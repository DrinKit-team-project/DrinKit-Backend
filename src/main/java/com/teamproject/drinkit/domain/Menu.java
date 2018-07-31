package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String krName;
    private String enName;

    private int calories;

    private String description;

    private Long userScore;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "cafe_id"))
    private Cafe cafe;

    @JsonIgnore
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    @Column
    private List<Price> pricePerSize = new ArrayList<>();

    @Embedded
    @Column
    private List<String> tagList = new ArrayList<>();

    @Embedded
    @Column
    private List<String> imageURL = new ArrayList<>();


    private boolean deleted = false;

    public Menu() {}
    public Menu(String krName, String enName, String description) {
        this.krName = krName;
        this.enName = enName;
        this.description = description;
    }
    public Menu(String krName, String enName, int calories, String description) {
        this.krName = krName;
        this.enName = enName;
        this.calories = calories;
        this.description = description;
    }

    public void addPrice(Price price) {
        this.pricePerSize.add(price);
    }
    public void addReview(Review review) {this.reviews.add(review);}

    public double calculateScore() {
        double result = 0.0;
        for (Review review: this.reviews) {
            result += review.getRatings();
        }
        return result / this.reviews.size();
    }

    public void registerCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public void addTag(String tag) {
        this.tagList.add(tag);
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    private boolean isDeleted() {
        return this.deleted;
    }

    public void deleteMenu() {
        this.deleted = true;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public String getDescription() {
        return description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Price> getPricePerSize() {
        return pricePerSize;
    }

}
