package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.dto.MenuDto;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Review> reviews = new ArrayList<>();

    private int reviewCount = 0;

    @ElementCollection
    private List<PricePerSize> pricePerSizes = new ArrayList<>();

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
        calculateScore(review.getRatings());
        reviewCount++;
        review.registerReview(this);
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

    //getter

    public Long getId() {
        return id;
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

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getTotalRatings() {
        return totalRatings;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public List<PricePerSize> getPricePerSizes() {
        return pricePerSizes;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public boolean isDeleted() {
        return deleted;
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
                reviewCount == menu.reviewCount &&
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

        return Objects.hash(super.hashCode(), id, krName, enName, calories, category, description, totalRatings, cafe, reviews, reviewCount, pricePerSizes, tagList, imageURLs, deleted);
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
                ", cafe=" + cafe +
                ", reviews=" + reviews +
                ", reviewCount=" + reviewCount +
                ", pricePerSizes=" + pricePerSizes +
                ", tagList=" + tagList +
                ", imageURLs=" + imageURLs +
                ", deleted=" + deleted +
                '}';
    }
}
