package com.example.beroepsproduct4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Rollatorhoortbij implements Parcelable {
    private Oudergegevens oudergegevens;
    private String rollator;

    public Rollatorhoortbij() {

    }

    public Rollatorhoortbij(Oudergegevens bsn, String rollator) {
        this.oudergegevens = bsn;
        this.rollator = rollator;

    }

    protected Rollatorhoortbij(Parcel in) {
        oudergegevens = in.readParcelable(Oudergegevens.class.getClassLoader());
        rollator = in.readString();
    }



    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(oudergegevens, flags);
        dest.writeString(rollator);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Rollatorhoortbij> CREATOR = new Creator<Rollatorhoortbij>() {
        @Override
        public Rollatorhoortbij createFromParcel(Parcel in) {
            return new Rollatorhoortbij(in);
        }

        @Override
        public Rollatorhoortbij[] newArray(int size) {
            return new Rollatorhoortbij[size];
        }
    };

    public Oudergegevens getOudergegevens() {
        return oudergegevens;
    }

    public void setOudergegevens(Oudergegevens oudergegevens) {
        this.oudergegevens = oudergegevens;
    }


    public String getrollator() {
        return rollator;
    }

    public void setRollator(String rollator) {
        this.rollator = rollator;
    }


    @Override
    public String toString() {
        return  oudergegevens + " "
               + rollator;
    }
}
