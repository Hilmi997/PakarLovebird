package com.example.hilmi.sistempakar.models;

/**
 * Created by User on 12/01/2020.
 */

public class PenyakitListModels {
    private  String ID;
    private String kode_penyakit;
    private String nama_penyakit;
    private String solusi;

    public PenyakitListModels(String ID, String kode_penyakit, String nama_penyakit, String solusi) {
        this.ID = ID;
        this.kode_penyakit = kode_penyakit;
        this.nama_penyakit = nama_penyakit;
        this.solusi = solusi;
    }

    public PenyakitListModels(String kode_penyakit, String nama_penyakit, String solusi) {
        this.kode_penyakit = kode_penyakit;
        this.nama_penyakit = nama_penyakit;
        this.solusi = solusi;
    }

    public String getID() {
        return ID;
    }


    public String getKode_penyakit() {
        return kode_penyakit;
    }


    public void setKode_penyakit(String kode_penyakit) {
        this.kode_penyakit = kode_penyakit;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }
}
