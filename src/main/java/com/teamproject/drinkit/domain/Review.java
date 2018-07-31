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
    private double ratings;

    @Lob
    @Column(name = "REVIEW_CONTENTS")
    private String contents;

    @Column(name = "REVIEW_DRINK_IMG_URL")
    private String drinkImgUrl;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "menu_id"))
    private Menu menu;

    private boolean deleted = false;

    public Review(){}

    public Review(Long id, double ratings, String contents, String drinkImgUrl){
        this.id = id;
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
    }

    public Review(double ratings, String contents, String drinkImgUrl){
        this(0L, ratings, contents, drinkImgUrl);
    }

    public static Review from(ReviewDto reviewDto){
        return new Review(reviewDto.getRatings(), reviewDto.getContents(), reviewDto.getDrinkImgUrl());
    }
}
