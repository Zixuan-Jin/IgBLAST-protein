package com.rapidnovor;


public class Main {

    public static void main(String[] args) {
	// write your code here
//        MatchedSequence temp = new MatchedSequence();
//        temp.setSequence("AILIADLHASDSHADBCDASD");
//        temp.setHead(4);
//        temp.setTail(240);
//        temp.setMatchPercentage((float) 97.6);
//        temp.setGeneFile("IGHV3-64*01");
//        temp.setQuery("AILADIADLASDSASDASD");
//        temp.setGermline("mouse");
//        System.out.println(temp);
//        Igblast ig = new IgblastImpl();
//        MatchedSequence result = ig.SingleMatch("DFGHJKFGH");
        BlastResult br = new BlastResult();
        br.setQuery("AAAABBBCC");
        br.setQuerySpecies("mouse");
        IgblastMatch matcha = new IgblastMatch();
        matcha.setMatch("ACABBBBCC");
        matcha.setRank(1);
        matcha.setAlignment("AAAABBB-CC", "ACA-BBBBCC");
        matcha.setMatchIndex(4, 249);
        matcha.setQueryIndex(12, 252);
        matcha.setTitle("IGLV5-52*01");
        matcha.setSpecies("mouse");
        matcha.setMatchRatio(0.923);
        br.addMatch(matcha);
        System.out.println(br);
    }
}
