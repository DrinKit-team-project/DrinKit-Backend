package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.service.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/cafes/{cafeId}/menus/{menuId}/reviews")
public class ApiReviewController {

    @Resource(name = "reviewService")
    private ReviewService reviewService;

    @PostMapping("")
    public Review addReview(Authentication authentication, @PathVariable Long cafeId, @PathVariable Long menuId, @RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(authentication, cafeId, menuId, Review.from(reviewDto));
    }

    @PutMapping("/{id}")
    public void editReview(Authentication authentication, @PathVariable Long cafeId, @PathVariable Long menuId, @PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 수정할 수 있지 않은가.

    }

    @DeleteMapping("/{id}")
    public void deleteReview(Authentication authentication, @PathVariable Long cafeId, @PathVariable Long menuId, @PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 삭제할 수 있지 않은가.
    }
}
