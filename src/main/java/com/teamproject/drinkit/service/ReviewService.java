package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.exception.NoSuchReviewException;
import com.teamproject.drinkit.security.token.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PreAuthorize("hasRole('ROLE_USER')")
@Transactional
public class ReviewService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private Account findLoginedUser(PostAuthorizationToken authentication) {
        PostAuthorizationToken token = authentication;
        return token.getAccountDetails().getAccount();
    }

    public Review addReview(Authentication authentication, Long cafeId, Long menuId, Review newReview) {
        Account writer = findLoginedUser((PostAuthorizationToken) authentication);
        newReview.registerWriter(writer);

        Menu menu = menuRepository.findByCafeIdAndId(cafeId, menuId).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
        newReview.registerMenu(menu);

        reviewRepository.save(newReview);
        return newReview;
    }

    public Review edit(Authentication authentication, Long cafeId, Long menuId, Long id, Review newOne){
        Account logined = findLoginedUser((PostAuthorizationToken)authentication);
        Review original = reviewRepository.findById(id).orElseThrow(() -> new NoSuchReviewException("찾는 리뷰가 존재하지 않습니다."));
        original.edit(logined, newOne);
        return null;
    }

    public void delete(Authentication authentication, Long cafeId, Long menuId, Long id, Review target){

    }

}
