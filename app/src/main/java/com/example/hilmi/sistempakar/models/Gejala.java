package com.example.hilmi.sistempakar.models;

import java.io.Serializable;


public class Gejala implements Serializable {
    private int ID;
    private String kode_gejala = null;
    private String nama_gejala = null;

    public Gejala(int ID, String kode_gejala, String nama_gejala) {
        this.ID = ID;
        this.kode_gejala = kode_gejala;
        this.nama_gejala = nama_gejala;
    }

    public Gejala(String kode_gejala, String nama_gejala) {

        this.kode_gejala = kode_gejala;
        this.nama_gejala = nama_gejala;
    }


    //id
    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }
    //akhir id



    public String getGid() {
        return kode_gejala;
    }

    public void setGid(String gid) {
        this.kode_gejala = gid;
    }

    public String getGejala() {
        return nama_gejala;
    }

    public void setGejala(String gejala) {
        this.nama_gejala = gejala;
    }
}
