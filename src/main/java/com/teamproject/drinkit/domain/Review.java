package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.dto.ReviewDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "REVIEW")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REVIEW_RATINGS")
    private Long ratings;

    @Lob
    @Column(name = "REVIEW_CONTENTS")
    private String contents;

    @Column(name = "REVIEW_DRINK_IMG_URL")
    private String drinkImgUrl;

    public Review(){}

    private Review(Long id, Long ratings, String contents, String drinkImgUrl){
        this.id = id;
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
    }

    private Review(Long ratings, String contents, String drinkImgUrl){
        this(0L, ratings, contents, drinkImgUrl);
    }

    public static Review from(ReviewDto reviewDto){
        return new Review(reviewDto.getRatings(), reviewDto.getContents(), reviewDto.getDrinkImgUrl());
    }
}
