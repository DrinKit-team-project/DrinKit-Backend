package com.teamproject.drinkit.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.teamproject.drinkit.domain.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReviewDto {

    @JsonProperty
    private Long id;

    @JsonProperty("ratings")
    private double ratings;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty
    private Account writer;

    @JsonProperty
    private boolean deleted;

    @JsonProperty
    private Menu menu;

    @JsonUnwrapped
    private UploadFileResponse uploadFileResponse;

    public ReviewDto() {}

    public ReviewDto(ReviewDto reviewDto, UploadFileResponse uploadFileResponse){
        this(reviewDto.id, reviewDto.ratings, reviewDto.contents, reviewDto.writer, reviewDto.deleted, reviewDto.menu);
        this.uploadFileResponse = uploadFileResponse;
    }

    public ReviewDto(Long id, double ratings, String contents, Account writer, boolean deleted, Menu menu, UploadFileResponse uploadFileResponse) {
        this(id, ratings, contents, writer, deleted, menu);
        this.uploadFileResponse = uploadFileResponse;
    }

    public ReviewDto(Long id, double ratings, String contents, Account writer, boolean deleted, Menu menu) {
        this.id = id;
        this.ratings = ratings;
        this.contents = contents;
        this.writer = writer;
        this.deleted = deleted;
        this.menu = menu;
    }

    public ReviewDto(double ratings, String contents) {
        this.ratings = ratings;
        this.contents = contents;
        this.deleted = false;
    }

    public ReviewDto(Long id, double ratings, String contents) {
        this(ratings, contents);
        this.id = id;
    }

    public ReviewDto(Long id, double ratings, String contents, UploadFileResponse uploadFileResponse) {
        this(ratings, contents, uploadFileResponse);
        this.id = id;
    }

    public ReviewDto(double ratings, String contents, UploadFileResponse uploadFileResponse) {
        this.ratings = ratings;
        this.contents = contents;
        this.deleted = false;
        this.uploadFileResponse = uploadFileResponse;
    }

    public static ReviewDto from(Review review){
        return new ReviewDto(review.getId(), review.getRatings(), review.getContents(), review.getWriter(), review.isDeleted(), review.getMenu(), review.getUploadFileResponse());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
