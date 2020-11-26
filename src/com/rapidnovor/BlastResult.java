package com.rapidnovor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

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

    public BlastResult(){
        matches = new ArrayList<IgblastMatch>();
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

    public void addMatch(IgblastMatch match) {
        this.matches.add(match);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
