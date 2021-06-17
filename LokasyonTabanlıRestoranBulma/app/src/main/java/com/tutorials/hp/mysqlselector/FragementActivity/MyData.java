package com.tutorials.hp.mysqlselector.FragementActivity;

/**
 * Created by Gamze on 3.03.2017.
 */
public class MyData {

    private int id;
    private String description,fiyat,image_link;

    public MyData(int id,String fiyat, String description ,String image_link) {
        this.id = id;
        this.fiyat=fiyat;
        this.description = description;
        this.image_link = image_link;
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
        this.fiyat =fiyat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}

