package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamproject.drinkit.dto.MenuDto;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
public class Menu extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String krName;
    private String enName;

    private int calories;

    private String category;

    private String description;

    private double totalRatings = 0.0;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "cafe_id"))
    private Cafe cafe;

    @OneToMany(mappedBy = "menu")
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    @Embedded
//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<PricePerSize> pricePerSizes = new ArrayList<>();

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

    public static Menu from(MenuDto menuDto) {
        return new Menu(menuDto.getKrName(), menuDto.getEnName(), menuDto.getCalories(), menuDto.getDescription(), menuDto.getCategory());
    }

    public void addPricePerSize(PricePerSize pricePerSize) {
        this.pricePerSizes.add(pricePerSize);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.registerMenu(this);
        calculateScore(review.getRatings());
    }

    public double calculateScore(double newRating) {
        double total = (totalRatings * (reviews.size() - 1)) + newRating;
        totalRatings = total / reviews.size();

        return totalRatings;
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


    //equals / hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return calories == menu.calories &&
                Double.compare(menu.totalRatings, totalRatings) == 0 &&
                deleted == menu.deleted &&
                Objects.equals(id, menu.id) &&
                Objects.equals(krName, menu.krName) &&
                Objects.equals(enName, menu.enName) &&
                Objects.equals(category, menu.category) &&
                Objects.equals(description, menu.description) &&
                Objects.equals(cafe, menu.cafe) &&
                Objects.equals(reviews, menu.reviews) &&
                Objects.equals(pricePerSizes, menu.pricePerSizes) &&
                Objects.equals(tagList, menu.tagList) &&
                Objects.equals(imageURLs, menu.imageURLs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, krName, enName, calories, category, description, totalRatings, cafe, reviews, pricePerSizes, tagList, imageURLs, deleted);
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
                ", totalRatings=" + totalRatings +
                ", cafe_id=" + cafe.getId() +
                ", reviews_size=" + reviews.size() +
                ", pricePerSizes=" + pricePerSizes +
                ", tagList_size=" + tagList.size() +
                ", imageURLs=" + imageURLs +
                ", deleted=" + deleted +
                '}';
    }
}
