package com.example.beroepsproduct4.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Oudergegevens implements Parcelable {


    private String oudernaam;
    private String bsn;
    private Zorgcentrum zorgcentrum;

    public Oudergegevens(String bsn, String oudernaam, Zorgcentrum zorgcentrum) {
        this.bsn = bsn;
        this.oudernaam = oudernaam;
        this.zorgcentrum = zorgcentrum;

    }

    public Oudergegevens(String bsn) {
        this.bsn = bsn;
    }

    public Oudergegevens() {

    }

    protected Oudergegevens(Parcel in) {
        oudernaam = in.readString();
        bsn = in.readString();
        zorgcentrum = in.readParcelable(Zorgcentrum.class.getClassLoader());
    }

    public static final Creator<Oudergegevens> CREATOR = new Creator<Oudergegevens>() {
        @Override
        public Oudergegevens createFromParcel(Parcel in) {
            return new Oudergegevens(in);
        }

        @Override
        public Oudergegevens[] newArray(int size) {
            return new Oudergegevens[size];
        }
    };




    public String getOudernaam() {
        return oudernaam;
    }

    public void setOudernaam(String oudernaam) {
        this.oudernaam = oudernaam;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public Zorgcentrum getZorgcentrum() {
        return zorgcentrum;
    }

    public void setZorgcentrum(Zorgcentrum zorgcentrum) {
        this.zorgcentrum = zorgcentrum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(oudernaam);
        parcel.writeString(bsn);
        parcel.writeParcelable(zorgcentrum,i);
    }

    @Override
    public String toString() {
        return bsn;
    }
}
