package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String krName;
    private String enName;

    private int calories;

    private String category;

    private String description;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "cafe_id"))
    private Cafe cafe;

    @JsonIgnore
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Review> reviews = new ArrayList<>();

    @Embedded
//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<PricePerSize> pricePerSizePerSizes = new ArrayList<>();

    @Embedded
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "menu_and_tag", joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tagList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "MENU_IMG_URL_LIST",
            joinColumns = @JoinColumn(name = "MENU_ID", foreignKey = @ForeignKey(name = "MENU_ID")))
    @Column(name = "MENU_IMG_URL")
    private List<String> imageURLs = new ArrayList<>();

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

    public Menu(String krName, String enName, int calories, String description, String category) {
        this.krName = krName;
        this.enName = enName;
        this.calories = calories;
        this.description = description;
        this.category = category;
    }

    public void addPricePerSize(PricePerSize pricePerSize) {
        this.pricePerSizePerSizes.add(pricePerSize);
    }
    public void addReview(Review review) {this.reviews.add(review);}

    public double calculateScore() {
        double result = 0.0;
        if (!isReviewExist()) {
            return result;
        }
        for (Review review: this.reviews) {
            result += review.getRatings();
        }
        return result / this.reviews.size();
    }

    private boolean isReviewExist() {
        if (this.reviews.size() > 0) {
            return true;
        }
        return false;
    }

    public void registerCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public void addTag(Tag tag) {
        this.tagList.add(tag);
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public void deleteMenu() {
        this.deleted = true;
    }

    //getter
    public Cafe getCafe() {
        return cafe;
    }
    public String getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public List<PricePerSize> getPricePerSizePerSizes() {
        return pricePerSizePerSizes;
    }
    public double getTotalRatings() {
        return this.calculateScore();
    }
    public String getKrName() {
        return krName;
    }
    public String getEnName() {
        return enName;
    }
    public int getCalories() {
        return calories;
    }
    public List<Tag> getTagList() {
        return tagList;
    }
    public List<String> getImageURLs() {
        return imageURLs;
    }
    private boolean isDeleted() {
        return this.deleted;
    }

    //equals / hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return calories == menu.calories &&
                deleted == menu.deleted &&
                Objects.equals(id, menu.id) &&
                Objects.equals(krName, menu.krName) &&
                Objects.equals(enName, menu.enName) &&
                Objects.equals(category, menu.category) &&
                Objects.equals(description, menu.description) &&
                Objects.equals(cafe, menu.cafe) &&
                Objects.equals(reviews, menu.reviews) &&
                Objects.equals(pricePerSizePerSizes, menu.pricePerSizePerSizes) &&
                Objects.equals(tagList, menu.tagList) &&
                Objects.equals(imageURLs, menu.imageURLs);
    }
    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, krName, enName, calories, category, description, cafe, reviews, pricePerSizePerSizes, tagList, imageURLs, deleted);
    }

    //toString

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", krName='" + krName + '\'' +
                ", enName='" + enName + '\'' +
                ", calories=" + calories +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", reviews=" + reviews +
                ", pricePerSizePerSizes=" + pricePerSizePerSizes +
                ", tagList=" + tagList +
                ", imageURLs=" + imageURLs +
                ", deleted=" + deleted +
                '}';
    }
}
