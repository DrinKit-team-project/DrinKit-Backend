package com.teamproject.drinkit.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int calories;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "menus"))
    private Cafe cafe;

    @Embedded
    private List<Price> pricePerSize = new ArrayList<>();

    @Column
    private String description;

    private boolean deleted = false;

    public Menu() {}
    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
