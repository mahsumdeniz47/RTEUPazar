package com.example.rteupazar;

import java.sql.SQLException;

public class RteuPazar {
    int id;
    String fiyat;
    String ozellik;
    String email;
    String adres;
    byte[] resim;


    public RteuPazar(int id,String fiyat, String ozellik, String email, String adres, byte[] resim) {
        this.id = id;
        this.fiyat = fiyat;
        this.ozellik = ozellik;
        this.email = email;
        this.adres = adres;
        this.resim = resim;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getOzellik() {
        return ozellik;
    }

    public void setOzellik(String ozellik) {
        this.ozellik = ozellik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public byte[] getResim() {
        return resim;
    }

    public void setResim(byte[] resim) {
        this.resim = resim;
    }

}
