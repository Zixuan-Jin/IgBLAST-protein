package com.rapidnovor;

import java.util.List;

/**
 * @description: Interface of wrapped IgBLAST
 * Created by Zixuan Jin on 2020-11-19.
 */
public interface IgblastProcessor {
    /**
     * function to get the result of IgBLAST in all database and without alignment
     * @param query query amino acid sequence
     * @return class of BlastResult
     */
    public BlastResult match(String query);
    /**
     * function to get the result of IgBLAST in certain database set alignment
     * @param query query amino acid sequence
     * @param germline set certain database to search
     * @param alignment set if alignment is needed
     * @return class of BlastResult
     */
    public BlastResult match(String query, String germline, boolean alignment);
}
