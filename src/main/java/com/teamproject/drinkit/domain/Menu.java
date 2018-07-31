package com.teamproject.drinkit.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int calories;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "cafe_id"))
    private Cafe cafe;

    @Embedded
    @Column
    private List<Price> pricePerSize = new ArrayList<>();

    private String description;

    private boolean deleted = false;

    public Menu() {}
    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<Price> getPricePerSize() {
        return pricePerSize;
    }
}
