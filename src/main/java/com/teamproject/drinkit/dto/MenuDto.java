package com.teamproject.drinkit.dto;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.PricePerSize;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.domain.Tag;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MenuDto {
    private String krName;
    private String enName;
    private int calories;
    private String description;
    private double totalRating;

    private List<Review> reviews = new ArrayList<>();
    private List<PricePerSize> pricePerSizePerSize = new ArrayList<>();
    private List<Tag> tagList = new ArrayList<>();
    private List<String> imageURLs = new ArrayList<>();

    private MenuDto(String krName, String enName, int calories, String description, double totalRating) {
        this.krName = krName;
        this.enName = enName;
        this.calories = calories;
        this.description = description;
        this.totalRating = totalRating;
    }

    //for menuDto
    public static MenuDto from(Menu menu) {
        return new MenuDto(menu.getKrName(), menu.getEnName(), menu.getCalories(), menu.getDescription(), menu.calculateScore());
    }
    public MenuDto copyReviews(Menu menu) {
        for (Review review : menu.getReviews()) {
            this.reviews.add(review);
        }
        return this;
    }
    public MenuDto copyPricePerSize(Menu menu) {
        for (PricePerSize pricePerSize : menu.getPricePerSizePerSizes()) {
            this.pricePerSizePerSize.add(pricePerSize);
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
}
