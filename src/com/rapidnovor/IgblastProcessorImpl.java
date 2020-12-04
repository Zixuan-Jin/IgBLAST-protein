package com.rapidnovor;

import com.rapidnovor.Exception.ExecuteException;
import com.rapidnovor.Exception.WrongInputMessage;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: Created by Zixuan Jin on 2020-11-26.
 */

public class IgblastProcessorImpl implements IgblastProcessor {
    /**
     * List to contain all the species, which can be added in the future
     */
    private final List<String> species = new ArrayList<>();

    /**
     * Set the database species
     */
    public IgblastProcessorImpl(){
        species.addAll(Arrays.asList("mouse", "human"));
    }

    /**
     *
     * @param query query amino acid sequence
     * @return the igblast result of the query
     * @throws WrongInputMessage input format is wrong, containing illegal amino acid
     * @throws ExecuteException errors occur when executing the program
     */
    @Override
    public BlastResult match(String query) throws WrongInputMessage, ExecuteException {
        BlastResult queryResult = new BlastResult();

        queryResult.setQuery(query);
        queryResult.setQuerySpecies("all");

        for(String specie:species){
            BlastResult partResult = match(query, specie, false);
            if(partResult != null){
                queryResult.addMatches(partResult.getMatches());
            }
        }
        return queryResult;
    }

    /**
     *
     * @param query query amino acid sequence
     * @param germline set certain database to search
     * @param alignment set if alignment is needed
     * @return the Igblast Result of the query
     * @throws WrongInputMessage input format is wrong, containing illegal amino acid
     * @throws ExecuteException errors occur when executing the program
     */
    @Override
    public BlastResult match(String query, String germline, boolean alignment) throws WrongInputMessage, ExecuteException {
        /*
         * result class to store all the result
         */
        BlastResult queryResult = new BlastResult();

        query = query.toUpperCase();
        queryResult.setQuery(query);
        queryResult.setQuerySpecies(germline);

        if(!checkFormat(query)){
            throw new WrongInputMessage("Wrong format of amino acid sequence");
        }

        if(!species.contains(germline)){
            throw new WrongInputMessage("Database does not contain that specie");
        }

        /*
         * Using file stream to parse data
         */
        try {
            OutputStream output = new FileOutputStream("queryFile");
            OutputStreamWriter writer = new OutputStreamWriter(output);
            writer.append(query);
            writer.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String cmdline = String.format("bin/igblastp -query queryFile " +
                        "-germline_db_V database/%s/%s_IGHV " +
                        "-outfmt 3 " +
                        "-organism %s " +
                        "-num_threads 8",
                germline,
                germline,
                germline);

        try {
            Process process = Runtime.getRuntime().exec(cmdline);
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errMessage = new StringBuilder();
            String line;
            while ((line = err.readLine()) != null){
                errMessage.append(line);
            }
            if(errMessage.length()>0){
                throw new ExecuteException(errMessage.toString());
            }

            String queryRegular = "^\\s+\\S+\\s+(\\d+)\\s+(\\S+)\\s+(\\d+)\\s*$";
            Pattern queryPattern = Pattern.compile(queryRegular);
            String matchRegular = "^V\\s+(\\S+)%\\s+\\S+\\s+(\\S+)\\s+(\\d+)\\s+(\\S+)\\s+(\\d+)\\s*$";
            Pattern matchPattern = Pattern.compile(matchRegular);
            /*
             * cache to store the IgblastMatch Objects
             */
            Map<String, IgblastMatch> cache = new HashMap<String, IgblastMatch>();
            
            StringBuilder queryAlignment = new StringBuilder();
            int queryHead = Integer.MAX_VALUE;
            int queryTail = 0;
            int rank = 1;

            /*
             * Read the outputStream of the process and convert it to IgblastMatch class by regular expression
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((line = in.readLine())!=null){
                if(line.equals("***** No hits found *****")){
                    return null;
                }
                
                Matcher queryMatcher = queryPattern.matcher(line);
                if(queryMatcher.find()){
                    queryAlignment.append(queryMatcher.group(2));
                    queryHead = Math.min(queryHead, Integer.parseInt(queryMatcher.group(1)));
                    queryTail = Math.max(queryTail, Integer.parseInt(queryMatcher.group(3)));
                }

                if(!line.equals("") && line.charAt(0) == 'V'){
                    Matcher matchMatcher = matchPattern.matcher(line);
                    if(matchMatcher.find()) {
                        /*
                        If the line contains a new match, construct a new IgblastMatch Object
                        */
                        if(!cache.containsKey(matchMatcher.group(2))) {
                            IgblastMatch newMatch = new IgblastMatch();
                            newMatch.setTitle(matchMatcher.group(2));
                            newMatch.setMatchRatio(Double.parseDouble(matchMatcher.group(1))/100.0);
                            newMatch.setAlignment(queryAlignment.toString(), matchMatcher.group(4));
                            newMatch.setQueryIndex(queryHead, queryTail);
                            newMatch.setMatchIndex(Integer.parseInt(matchMatcher.group(3)),
                                    Integer.parseInt(matchMatcher.group(5)));
                            newMatch.setRank(rank);
                            newMatch.setSpecies(germline);
                            rank++;
                            cache.put(matchMatcher.group(2), newMatch);
                        }
                        /*
                        If the line updates a new match, updates the IgblastMatch Object
                        */
                        else {
                            IgblastMatch temp = cache.get(matchMatcher.group(2));
                            temp.setQueryIndex(queryHead, queryTail);
                            temp.setAlignment(queryAlignment.toString(),
                                    temp.getMatchAlignment()+matchMatcher.group(4));
                            temp.setMatchIndex(temp.getMatchHead(), Integer.parseInt(matchMatcher.group(5)));
                        }
                    }
                }
            }
            for(IgblastMatch temp:cache.values()){
                StringBuilder matchAlignment = new StringBuilder();
                String matchQuery = temp.getMatchAlignment();
                for(int i=0;i<queryAlignment.length();i++){
                    if(matchQuery.charAt(i)=='.'){
                        matchAlignment.append(queryAlignment.charAt(i));
                    }else{
                        matchAlignment.append(matchQuery.charAt(i));
                    }
                }
                temp.setAlignment(queryAlignment.toString(), matchAlignment.toString());
                temp.setMatch(matchAlignment.toString().replaceAll("-",""));
                queryResult.addMatch(temp);
            }
            return queryResult;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * @param query input query String
     * @return if the query String is in standard format
     */
    private boolean checkFormat(String query) {
        String pattern = "^[ARNDCEQGHILKMFPSTWYV]+$";
        Pattern r = Pattern.compile(pattern);
        return r.matcher(query).find();
    }
}
