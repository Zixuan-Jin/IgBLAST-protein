package com.rapidnovor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.tools.javac.util.Pair;

import java.util.*;

/**
 * @description: Object to store the result of IgBLAST
 * Created by Zixuan Jin on 2020-11-23.
 */
public class BlastResult {
    /**initial query amino acid sequence*/
    private String query;
    /**query database*/
    private String querySpecies;
    /**version of BLAST result format*/
    private final String version = "v1.0";
    /**list of IgblastMatch to store the match result*/
    private List<IgblastMatch> matches;
//
//    private List<Pair<Double, IgblastMatch>> cache;

    public BlastResult(){
        matches = new ArrayList<>();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuerySpecies() {
        return querySpecies;
    }

    public void setQuerySpecies(String querySpecies) {
        this.querySpecies = querySpecies;
    }

    public List<IgblastMatch> getMatches() {
        return matches;
    }

    public IgblastMatch getMatch(int rank){
        return matches.get(rank);
    }

    public void addMatch(IgblastMatch match) {
        PriorityQueue<IgblastMatch> cache = new PriorityQueue<>(3, new MatchComparator());
        cache.addAll(matches);
        matches.clear();
        if(cache.size()>=3){
            cache.offer(match);
            cache.poll();
        }else{
            cache.add(match);
        }
        int rank = 3;
        while(!cache.isEmpty()){
            IgblastMatch temp = cache.poll();
            temp.setRank(rank);
            rank--;
            matches.add(0, temp);
        }
    }

    public void addMatches(List<IgblastMatch> ms) {
        for(IgblastMatch temp: ms){
            addMatch(temp);
        }
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

class MatchComparator implements Comparator<IgblastMatch>{

    @Override
    public int compare(IgblastMatch o1, IgblastMatch o2) {
        return Double.compare(o1.getMatchRatio(), o2.getMatchRatio());
    }
}
