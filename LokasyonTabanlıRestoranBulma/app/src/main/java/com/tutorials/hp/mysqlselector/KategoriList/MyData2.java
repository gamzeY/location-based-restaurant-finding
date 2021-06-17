package com.tutorials.hp.mysqlselector.KategoriList;

import com.tutorials.hp.mysqlselector.FragementActivity.MyData;

/**
 * Created by Gamze on 3.03.2017.
 */
public class MyData2  {

    private int id;
    private String adi,image_link;

    public MyData2(int id, String adi,  String image_link) {
        this.id = id;
        this.adi=adi;
        this.image_link = image_link;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAdi() {
        return adi;
    }
    public void setAdi(String adi) {
        this.adi =adi;
    }
    public String getImage_link() {
        return image_link;
    }
    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}

