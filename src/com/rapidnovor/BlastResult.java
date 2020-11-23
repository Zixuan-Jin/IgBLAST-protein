package com.rapidnovor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * @description: Object to store the result of IgBLAST
 * Created by Zixuan Jin on 2020-11-23.
 */
public class BlastResult {
    private String query;
    private String querySpecies;
    private final String version = "v1.0";
    private List<IgblastMatch> matches;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String toString() {
        return gson.toJson(this);
    }
}
