package com.example.beroepsproduct4.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Zorgcentrum implements Parcelable  {
    private String afdeling;
    private String zorgcentrum;

    public Zorgcentrum(String afdeling, String zorcentrum) {
        this.afdeling = afdeling;
        this.zorgcentrum = zorcentrum;
    }

    public Zorgcentrum() {

    }

    protected Zorgcentrum(Parcel in) {
        afdeling = in.readString();
        zorgcentrum = in.readString();
    }

    public static final Creator<Zorgcentrum> CREATOR = new Creator<Zorgcentrum>() {
        @Override
        public Zorgcentrum createFromParcel(Parcel in) {
            return new Zorgcentrum(in);
        }

        @Override
        public Zorgcentrum[] newArray(int size) {
            return new Zorgcentrum[size];
        }
    };

    public String getAfdeling() {
        return afdeling;
    }

    public void setAfdeling(String afdeling) {
        this.afdeling = afdeling;
    }

    public String getZorgcentrum() {
        return zorgcentrum;
    }

    public void setZorgcentrum(String zorgcentrum) {
        this.zorgcentrum = zorgcentrum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(afdeling);
        parcel.writeString(zorgcentrum);
    }

    @Override
    public String toString() {
        return afdeling + " "+ zorgcentrum;
    }
}
