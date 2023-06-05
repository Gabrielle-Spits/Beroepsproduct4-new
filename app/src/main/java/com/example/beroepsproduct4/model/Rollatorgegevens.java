package com.example.beroepsproduct4.model;

import java.time.LocalDateTime;

public class Rollatorgegevens {
    private Rollatorhoortbij rollator;
    private LocalDateTime datum;


    public Rollatorhoortbij getRollator() {
        return rollator;
    }

    public void setRollator(Rollatorhoortbij rollator) {
        this.rollator = rollator;
    }

    public LocalDateTime datum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
}
