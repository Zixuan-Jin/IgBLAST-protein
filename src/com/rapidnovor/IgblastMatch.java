package com.rapidnovor;

/**
 * @description:
 * Created by Zixuan Jin on 2020-11-20.
 */
public class IgblastMatch {
    private class Detail{
        private String title;
        private double matchRatio;
        private String species;
        private int queryHead;
        private int queryTail;
        private int matchHead;
        private int matchTail;
        Detail(String title, String species, double matchRatio){
            this.title = title;
            this.species = species;
            this.matchRatio = matchRatio;
        }
        public void SetQueryIndex(int head, int tail){
            this.queryHead = head;
            this.queryTail = tail;
        }

        public void SetMatchIndex(int head, int tail){
            this.matchHead = head;
            this.matchTail = tail;
        }


    }

}
