package com.teamproject.drinkit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.Review;
import lombok.Getter;

@Getter
public class ReviewDto {
    @JsonProperty
    private double ratings;

    @JsonProperty
    private String contents;

    @JsonProperty
    private String drinkImgUrl;

    @JsonIgnore
    private Account writer;

    public ReviewDto() {}

    public ReviewDto(double ratings, String contents, String drinkImgUrl){
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
    }

    public static ReviewDto from(Review review){
        return new ReviewDto(review.getRatings(), review.getContents(), review.getDrinkImgUrl());
    }
}
