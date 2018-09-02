package com.teamproject.drinkit.domain;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PricePerSize {

    private String size;
    private int cost;

    public PricePerSize() {}
    public PricePerSize(String size, int cost) {
        this.size = size;
        this.cost = cost;
    }

    //getter
    public String getSize() {
        return size;
    }
    public int getCost() {
        return cost;
    }
}
