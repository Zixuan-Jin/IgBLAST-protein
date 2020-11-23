package com.rapidnovor;

import java.util.List;

/**
 * @description: Interface of wrapped IgBLAST
 * Created by Zixuan Jin on 2020-11-19.
 */
public interface IgblastProcessor {
    public BlastResult match(String query);

    public BlastResult match(String query, String germline, boolean alignment);
}
