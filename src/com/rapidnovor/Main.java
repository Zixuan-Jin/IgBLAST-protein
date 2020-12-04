package com.rapidnovor;


import com.rapidnovor.Exception.ExecuteException;
import com.rapidnovor.Exception.WrongInputMessage;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException, WrongInputMessage, ExecuteException {
        String query = "QVQLVQSGAEVKKPGASGISWVRQAPGQGLEWMGWISAYNGNTNY" +
                "AQKLQGRVTMTTDTSTSTAYMELRSLRSDDTAWIWIVYYCARGIVGATGIVGATGIVGAT";
        IgblastProcessor igp = new IgblastProcessorImpl();
        System.out.println(igp.match(query, "human", true));
        String query2 = "EVQLQQFGPELVKPGASVKISCKASMDWVKQSHGKSLEWIGDINPNNGGTIY" +
                "NQKFKGKATLTVDKSSSTAAELVYMELR";
        System.out.println("-------------------------------------------");
        System.out.println(igp.match(query2, "mouse", true));
        System.out.println("-------------------------------------------");
        System.out.println(igp.match(query2));
    }
}
