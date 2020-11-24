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

        public void setQueryIndex(int head, int tail){
            this.queryHead = head;
            this.queryTail = tail;
        }

        public void setMatchIndex(int head, int tail){
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

    public void setMatch(String match) {
        this.match = match;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getMatch() {
        return match;
    }

    public int getRank() {
        return rank;
    }

    public Detail getDetail() {
        return detail;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(String query, String match){
        alignment = new Alignment(query, match);
    }

    public void setTitle(String title) {
        detail.setTitle(title);
    }

    public void setMatchRatio(double matchRatio) {
        detail.setMatchRatio(matchRatio);
    }

    public void setSpecies(String species) {
        detail.setSpecies(species);
    }

    public void setQueryIndex(int head, int tail){
        detail.setQueryIndex(head, tail);
    }

    public void setMatchIndex(int head, int tail){
        detail.setMatchIndex(head, tail);
    }

    public String getTitle() {
        return detail.getTitle();
    }

    public double getMatchRatio() {
        return detail.getMatchRatio();
    }

    public String getSpecies() {
        return detail.getSpecies();
    }

    public int getQueryHead() {
        return detail.getQueryHead();
    }

    public int getQueryTail() {
        return detail.getQueryTail();
    }

    public int getMatchHead() {
        return detail.getMatchHead();
    }

    public int getMatchTail() {
        return detail.getMatchTail();
    }

    public IgblastMatch(){
        detail = new Detail();
    }
}
