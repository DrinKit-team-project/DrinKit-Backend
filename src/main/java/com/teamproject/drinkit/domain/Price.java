package com.teamproject.drinkit.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Price {

    private String size;
    private int cost;

    public Price() {}
    public Price(size, cost) {
        this.size = size;
        this.cost = cost;
    }
}
