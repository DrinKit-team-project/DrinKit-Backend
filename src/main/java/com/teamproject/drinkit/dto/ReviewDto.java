package com.teamproject.drinkit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Review;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReviewDto {

    @JsonProperty
    private Long id;

    @JsonProperty
    private double ratings;

    @JsonProperty
    private String contents;

    @JsonProperty
    private String drinkImgUrl;

    @JsonProperty
    private Account writer;

    @JsonProperty
    private boolean deleted;

    @JsonProperty
    private Menu menu;

    public ReviewDto() {}

    public ReviewDto(Long id, double ratings, String contents, String drinkImgUrl, Account writer, boolean deleted, Menu menu) {
        this.id = id;
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
        this.writer = writer;
        this.deleted = deleted;
        this.menu = menu;
    }

    public ReviewDto(double ratings, String contents, String drinkImgUrl) {
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
        this.deleted = false;
    }

    public static ReviewDto from(Review review){
        return new ReviewDto(review.getId(), review.getRatings(), review.getContents(), review.getDrinkImgUrl(), review.getWriter(), review.isDeleted(), review.getMenu());
    }

}
