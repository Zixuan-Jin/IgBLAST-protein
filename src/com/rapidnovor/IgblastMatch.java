package com.rapidnovor;

/**
 * @description: Object to store one single match result
 * Created by Zixuan Jin on 2020-11-20.
 */
public class IgblastMatch {

    private static class Detail{
        private String title;
        private double matchRatio;
        private String species;
        private int queryHead;
        private int queryTail;
        private int matchHead;
        private int matchTail;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMatchRatio(double matchRatio) {
            this.matchRatio = matchRatio;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        public void SetQueryIndex(int head, int tail){
            this.queryHead = head;
            this.queryTail = tail;
        }

        public void SetMatchIndex(int head, int tail){
            this.matchHead = head;
            this.matchTail = tail;
        }

        public String getTitle() {
            return title;
        }

        public double getMatchRatio() {
            return matchRatio;
        }

        public String getSpecies() {
            return species;
        }

        public int getQueryHead() {
            return queryHead;
        }

        public int getQueryTail() {
            return queryTail;
        }

        public int getMatchHead() {
            return matchHead;
        }

        public int getMatchTail() {
            return matchTail;
        }
    }

    private static class Alignment{

        private final String query;

        private final String match;

        Alignment(String query, String match){
            this.query = query;
            this.match = match;
        }

        public String getQuery() {
            return query;
        }

        public String getMatch() {
            return match;
        }
    }

    private String match;

    private int rank;

    private Detail detail;

    private Alignment alignment;





}
