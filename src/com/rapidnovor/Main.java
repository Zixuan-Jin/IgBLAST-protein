package com.rapidnovor;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
//        BlastResult br = new BlastResult();
//        br.setQuery("AAAABBBCC");
//        br.setQuerySpecies("mouse");
//        IgblastMatch matcha = new IgblastMatch();
//        matcha.setMatch("ACABBBBCC");
//        matcha.setRank(1);
//        matcha.setAlignment("AAAABBB-CC", "ACA-BBBBCC");
//        matcha.setMatchIndex(4, 249);
//        matcha.setQueryIndex(12, 252);
//        matcha.setTitle("IGLV5-52*01");
//        matcha.setSpecies("mouse");
//        matcha.setMatchRatio(0.923);
//        br.addMatch(matcha);
//        System.out.println(br);
        String query = "QVQLVQSGAEVKKPGASVKVSCKASGYTFTSYGISWVRQAPGQGLEWMGWISAYNGNTNY" +
                "AQKLQGRVTMTTDTSTSTAYMELRSLRSDDTAWIWIVYYCARGIVGATGIVGATGIVGAT";
        query = query.toUpperCase();
        try {
            OutputStream output = new FileOutputStream("queryFile");
            OutputStreamWriter writer = new OutputStreamWriter(output);
            writer.append(query);
            writer.close();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String germline = "human";
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
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (in.readLine()!=null){
                System.out.println(in.readLine());
            }
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while (err.readLine()!=null){
                System.out.println(err.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
