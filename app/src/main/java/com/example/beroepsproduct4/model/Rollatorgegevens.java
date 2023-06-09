package com.example.beroepsproduct4.model;

import java.time.LocalDateTime;

public class Rollatorgegevens {
    private Rollatorhoortbij rollator;
    private String datum;


    public Rollatorgegevens() {

    }


    public Rollatorhoortbij getRollator() {
        return rollator;
    }

    public void setRollator(Rollatorhoortbij rollator) {
        this.rollator = rollator;
    }

    public Rollatorhoortbij rollator() {
        return rollator;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return  "Rollator gevallen" + rollator + " op " +datum;
    }
}
