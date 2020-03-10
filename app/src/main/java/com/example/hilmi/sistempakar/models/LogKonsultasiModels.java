package com.example.hilmi.sistempakar.models;

import java.io.Serializable;

/**
 * Created by User on 02/02/2020.
 */

public class LogKonsultasiModels implements Serializable {
    public String resultID;
    public String resultPenyakit;
    public String resultGejala;
    public String resultSolusi;

    public LogKonsultasiModels(String resultID, String resultPenyakit, String resultGejala, String resultSolusi) {
        this.resultID = resultID;
        this.resultPenyakit = resultPenyakit;
        this.resultGejala = resultGejala;
        this.resultSolusi = resultSolusi;
    }

    public String getResultPenyakit() {
        return resultPenyakit;
    }

    public void setResultID(String resultID) {
        this.resultID = resultID;
    }
    public String getResultID() {
        return resultID;
    }

    public void setResultPenyakit(String resultPenyakit) {
        this.resultPenyakit = resultPenyakit;
    }

    public String getResultGejala() {
        return resultGejala;
    }

    public void setResultGejala(String resultGejala) {
        this.resultGejala = resultGejala;
    }

    public String getResultSolusi() {
        return resultSolusi;
    }

    public void setResultSolusi(String resultSolusi) {
        this.resultSolusi = resultSolusi;
    }
}
