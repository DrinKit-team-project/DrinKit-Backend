package com.teamproject.drinkit.dto;

import com.teamproject.drinkit.domain.Review;
import lombok.Getter;

@Getter
public class ReviewDto {
    private double ratings;
    private String contents;
    private String drinkImgUrl;

    private ReviewDto(double ratings, String contents, String drinkImgUrl){
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
    }

    public static ReviewDto from(Review review){
        return new ReviewDto(review.getRatings(), review.getContents(), review.getDrinkImgUrl());
    }
}
