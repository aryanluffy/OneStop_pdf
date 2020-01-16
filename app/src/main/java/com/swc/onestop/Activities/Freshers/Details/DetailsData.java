package com.swc.onestop.Activities.Freshers.Details;


import android.os.Parcel;
import android.os.Parcelable;

public class DetailsData implements Parcelable {
    public final String title;
    public final String text;

    public  String freshersPostLink  ="";

    public DetailsData(String title, String text,String freshersPostLink) {
        this.title = title;
        this.text = text;
        this.freshersPostLink=freshersPostLink;
    }

    private DetailsData(Parcel in) {
        title = in.readString();
        text = in.readString();
        freshersPostLink = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(text);
        parcel.writeString(freshersPostLink);
    }

    public static final Creator<DetailsData> CREATOR = new Creator<DetailsData>() {
        @Override
        public DetailsData createFromParcel(Parcel parcel) {
            return new DetailsData(parcel);
        }

        @Override
        public DetailsData[] newArray(int size) {
            return new DetailsData[size];
        }
    };

}
