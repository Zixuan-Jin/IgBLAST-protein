package com.rapidnovor;


import com.rapidnovor.Exception.ExecuteException;
import com.rapidnovor.Exception.WrongInputMessage;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, WrongInputMessage, ExecuteException {
        IgblastProcessor igp = new IgblastProcessorImpl();
        String query = "QVQLVQSGAEVKKPGASGISWVRQAPGQGLEWMGWISAYNGNTNY" +
                "AQKLQGRVTMTTDTSTSTAYMELRSLRSDDTAWIWIVYYCARGIVGATGIVGATGIVGAT";
//        System.out.println(igp.match(query, "human", true));
//
//
        String query2 = "EVQLQQFGPELVKPGASVKISCKASMDWVKQSHGKSLEWIGDINPNNGGTIY" +
                "NQKFKGKATLTVDKSSSTAAELVYMELR";
//        System.out.println(igp.match(query2));
        String query_test = "LQQFGPELVKP";
        System.out.println(igp.match(query_test,"rat", true));
    }
}
