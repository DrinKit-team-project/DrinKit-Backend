package com.teamproject.drinkit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.PricePerSize;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.domain.Tag;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
    private String krName;
    private String enName;
    private int calories;
    private String description;
    private double totalRating;
    private String category;

    @JsonIgnore
    private List<Review> reviews = new ArrayList<>();

    private List<PricePerSize> pricePerSize = new ArrayList<>();
    private List<Tag> tagList = new ArrayList<>();
    private List<String> imageURLs = new ArrayList<>();

    private MenuDto(String krName, String enName, int calories, String description, double totalRating, String category) {
        this.krName = krName;
        this.enName = enName;
        this.calories = calories;
        this.description = description;
        this.totalRating = totalRating;
        this.category = category;
    }

    private MenuDto(String krName, String enName, int calories, String description, double totalRating, String category, List<Tag> tagList, List<PricePerSize> pricePerSize) {
        this.krName = krName;
        this.enName = enName;
        this.calories = calories;
        this.description = description;
        this.totalRating = totalRating;
        this.category = category;
        this.tagList = tagList;
        this.pricePerSize = pricePerSize;
    }

    //for menuDto
    public static MenuDto from(Menu menu) {
        return new MenuDto(menu.getKrName(), menu.getEnName(), menu.getCalories(), menu.getDescription(), menu.getTotalRatings(), menu.getCategory(), menu.getTagList(), menu.getPricePerSizes());
    }
    public MenuDto copyReviews(Menu menu) {
        for (Review review : menu.getReviews()) {
            this.reviews.add(review);
        }
        return this;
    }
    public MenuDto copyPricePerSize(Menu menu) {
        for (PricePerSize pricePerSize : menu.getPricePerSizes()) {
            this.pricePerSize.add(pricePerSize);
        }
        return this;
    }
    public MenuDto copyTagList(Menu menu) {
        for (Tag tag : menu.getTagList()) {
            this.tagList.add(tag);
        }
        return this;
    }
    public MenuDto copyImageURLs(Menu menu) {
        for (String url : menu.getImageURLs()) {
            this.imageURLs.add(url);
        }
        return this;
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

    public String getDescription() {
        return description;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public String getCategory() {
        return category;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<PricePerSize> getPricePerSizePerSize() {
        return pricePerSize;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "krName='" + krName + '\'' +
                ", enName='" + enName + '\'' +
                ", calories=" + calories +
                ", description='" + description + '\'' +
                ", totalRating=" + totalRating +
                ", category='" + category + '\'' +
                ", reviews=" + reviews +
                ", pricePerSize=" + pricePerSize.toString() +
                ", tagList=" + tagList +
                ", imageURLs=" + imageURLs +
                '}';
    }
}
