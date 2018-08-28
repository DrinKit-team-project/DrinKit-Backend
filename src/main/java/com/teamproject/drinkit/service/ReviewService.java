package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.NoLoginedUserException;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.exception.NoSuchReviewException;
import com.teamproject.drinkit.security.AccountDetails;
import com.teamproject.drinkit.security.jwt.JwtDecoder;
import com.teamproject.drinkit.security.jwt.JwtExtractor;
import com.teamproject.drinkit.security.token.PostAuthorizationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PreAuthorize("hasRole('ROLE_USER')")
@Transactional
public class ReviewService {
    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtDecoder decoder;

    private Account findLoginedUser(String header) {
        AccountDetails accountDetails = decoder.decode(JwtExtractor.extractJwtToken(header));
        log.debug("유저이름 : {}", accountDetails.getUsername());
        return accountRepository.findByUserId(accountDetails.getUsername()).orElseThrow(() -> new NoLoginedUserException("현재 로그인한 유저가 존재하지 않습니다."));
    }

    public Review addReview(String header, Long cafeId, Long menuId, ReviewDto newReviewDto) {
        Review newReview = Review.from(newReviewDto);

        Account writer = findLoginedUser(header);
        writer.writeReview(newReview);

        Menu menu = menuRepository.findByCafeIdAndId(cafeId, menuId).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
        menu.addReview(newReview);
        return newReview;
    }

    public Review edit(String header, Long cafeId, Long menuId, Long id, double ratings, String contents){
        Account logined = findLoginedUser(header);

        Menu menu = menuRepository.findByCafeIdAndId(cafeId, menuId).orElseThrow(() -> new NoSuchMenuException("no menu exist."));

        Review original = reviewRepository.findByMenuIdAndId(menu.getId(), id).orElseThrow(() -> new NoSuchReviewException("찾는 리뷰가 존재하지 않습니다."));
        return original.edit(logined, ratings, contents);
    }

    public void delete(String header, Long cafeId, Long menuId, Long id) {
        Account logined = findLoginedUser(header);

        Menu menu = menuRepository.findByCafeIdAndId(cafeId, menuId).orElseThrow(() -> new NoSuchMenuException("no menu exist."));

        Review target = reviewRepository.findByMenuIdAndId(menu.getId(), id).orElseThrow(() -> new NoSuchReviewException("찾는 리뷰가 존재하지 않습니다."));
        target.delete(logined);
    }
}
