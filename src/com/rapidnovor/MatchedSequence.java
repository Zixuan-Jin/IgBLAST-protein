package com.rapidnovor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @description: Object to store a single matched result of IgBLASTp
 * Created by Zixuan Jin on 2020-11-19.
 */
public class MatchedSequence {


    private class Alignment {
        private String querySeq;
        private String relation = "";
        private String alignSeq;

        Alignment(String querySeq, String alignSeq) {
            this.querySeq = querySeq;
            this.alignSeq = alignSeq;
            for(int i=0;i<querySeq.length();i++){
                if(querySeq.charAt(i)==alignSeq.charAt(i)){
                    relation += '|';
                }else{
                    relation += ' ';
                }
            }
        }

        public String getQuerySeq() {
            return querySeq;
        }

        public String getAlignSeq() {
            return alignSeq;
        }
    }
    /*Percentage of the portion of the result which matched the query sequence*/
    private float matchPercentage;
    /*Name of the matched sequence */
    private String geneFile;
    /*Database that which germline use*/
    private String germline;
    /*Initial sequence used to make IgBLAST*/
    private String query;
    /*Original amino acid sequence of matched part */
    private String sequence;
    /*Start position of the matched part in geneFile*/
    private int head;
    /*End position of the matched part in geneFile*/
    private int tail;
    /*Alignment amino acid sequence of matched part */
    private final Alignment alignment = new Alignment("AILADIADL-ASDS-ASD---ASD","ABL--IADLHAHDSHA-DBCDASD");

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
