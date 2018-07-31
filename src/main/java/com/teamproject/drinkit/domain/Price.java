package com.teamproject.drinkit.domain;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Price {

    private String size;
    private int cost;

    public Price() {}
    public Price(String size, int cost) {
        this.size = size;
        this.cost = cost;
    }
}
