package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.dto.ReviewDto;
import lombok.Getter;

@Getter
public class CreateReviewResponse {

    private ReviewDto reviewDto;
    private UploadFileResponse uploadFileResponse;

    public CreateReviewResponse(ReviewDto reviewDto, UploadFileResponse uploadFileResponse){
        this.reviewDto = reviewDto;
        this.uploadFileResponse = uploadFileResponse;
    }
}
