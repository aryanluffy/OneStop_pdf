package com.swc.onestop.Activities.Freshers.inner;

public class InnerData {

    public final String title;
    public final String name;
    public final String address;
    public final String avatarUrl;

    public final int order;
    public final String innerID;
    public final String outerID;

    public InnerData(String title, String name, String address, String avatarUrl,String innerID,String outerID,int order) {
        this.title = title;
        this.name = name;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.innerID=innerID;
        this.outerID=outerID;
        this.order=order;
    }
}
