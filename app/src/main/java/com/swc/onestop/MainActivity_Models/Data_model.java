package com.swc.onestop.MainActivity_Models;

public class Data_model {

    String title;
    String subtitle;
    String desc;
    String image;
    String dp;
    String id;
    String link="";  //****testing

    public Data_model(String title, String subtitle, String desc, String dp, String image, String id,String link) {
        this.title = title;
        this.subtitle = subtitle;
        this.desc = desc;
        this.image = image;
        this.dp = dp;
        this.id = id;
        this.link=link;  //****testing
    }

    public String getTitle() {
        return title;
    }

    public String getID() {
        return id;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getDesc() {
        return desc;
    }

    public String getdp() {
        return dp;
    }

    public String getImage() {
        return image;
    }

    public String getLink(){return link;}    //***testing
}