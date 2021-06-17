package com.tutorials.hp.mysqlselector.resanasayfa.m_DataObject;

/**
 * Created by Gamze on 19.03.2017.
 */
public class Spececraft {
    int id,number;
    String name,imageUrl,aciklama,adres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama =aciklama;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres= adres;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
