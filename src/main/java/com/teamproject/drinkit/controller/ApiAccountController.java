package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class ApiAccountController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}/reviews")
    public Iterable<Review> showReviews(@PathVariable Long id){
        return reviewService.findAllByWriter(id);
    }


}
