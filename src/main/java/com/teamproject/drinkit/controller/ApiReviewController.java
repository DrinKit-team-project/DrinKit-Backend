package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.security.jwt.JwtDecoder;
import com.teamproject.drinkit.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/cafes/{cafeId}/menus/{menuId}/reviews")
public class ApiReviewController {
    private static final Logger log = LoggerFactory.getLogger(ApiReviewController.class);

    @Resource(name = "reviewService")
    private ReviewService reviewService;

    @PostMapping("")
    public ReviewDto addReview(@RequestHeader("Authorization") String header, @PathVariable Long cafeId, @PathVariable Long menuId, @RequestBody ReviewDto reviewDto) {
        return reviewService.addReview(header, cafeId, menuId, reviewDto);
    }

    @PutMapping("")
    public ReviewDto editReview(@RequestHeader("Authorization") String header, @PathVariable Long cafeId, @PathVariable Long menuId, @RequestBody ReviewDto target) {
        return reviewService.edit(header, cafeId, menuId, target);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@RequestHeader("Authorization") String header, @PathVariable Long cafeId, @PathVariable Long menuId, @PathVariable Long id) {
        reviewService.delete(header, cafeId, menuId, id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
}
