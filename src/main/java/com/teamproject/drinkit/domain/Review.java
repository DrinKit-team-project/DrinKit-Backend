package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.AuthorizationException;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "REVIEW")
public class Review extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REVIEW_RATINGS", nullable=false)
    private double ratings;

    @Lob
    @Column(name = "REVIEW_CONTENTS", nullable=false)
    private String contents;

    @Column(name = "REVIEW_DRINK_IMG_URL")
    private String drinkImgUrl;

    @ManyToOne
    @JoinColumn(name = "fk_account")
    @JsonIgnore
    private Account writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "menu_id"))
    @JsonIgnore
    private Menu menu;

    @JsonIgnore
    private boolean deleted = false;

    public Review(){}

    public Review(Long id, double ratings, String contents, String drinkImgUrl){
        this.id = id;
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
    }

    public Review(double ratings, String contents, String drinkImgUrl){
        this.ratings = ratings;
        this.contents = contents;
        this.drinkImgUrl = drinkImgUrl;
    }

    public static Review from(ReviewDto reviewDto){
        return new Review(0L, reviewDto.getRatings(), reviewDto.getContents(), reviewDto.getDrinkImgUrl());
    }

    public void registerMenu(Menu menu) {
        this.menu = menu;
    }

    public void registerWriter(Account writer) {
        this.writer = writer;
    }

    public Review edit(Account logined, double ratings, String contents){
        if(!isSameAccount(logined)){
            throw new AuthorizationException("로그인 유저와 글쓴이가 다릅니다.");
        }
        this.ratings = ratings;
        this.contents = contents;
        return this;
    }

    public void delete(Account logined){
        if(!isSameAccount(logined)){
            throw new AuthorizationException("로그인 유저와 글쓴이가 다릅니다.");
        }
        this.deleted = true;
    }

    private boolean isSameAccount(Account logined){
        return this.writer.equals(logined);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", ratings=" + ratings +
                ", contents='" + contents + '\'' +
                ", drinkImgUrl='" + drinkImgUrl + '\'' +
                ", deleted=" + deleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
