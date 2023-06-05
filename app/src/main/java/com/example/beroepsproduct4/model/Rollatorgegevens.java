package com.example.beroepsproduct4.model;

import java.time.LocalDateTime;

public class Rollatorgegevens {
    private String rollator;
    private LocalDateTime datum;


    public String getrollator() {
        return rollator;
    }

    public void setRollator(String rollator) {
        this.rollator = rollator;
    }

    public LocalDateTime datum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
}
