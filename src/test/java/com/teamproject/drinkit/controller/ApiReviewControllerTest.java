package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.support.test.AuthenticationTestSupporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiReviewControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ApiReviewControllerTest.class);

    @Autowired
    private TestRestTemplate template;

    private HttpHeaders buildRequestHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("Authorization", "Bearer " + AuthenticationTestSupporter.requestJwtToken());
        return requestHeaders;
    }

    @Test
    public void ADD_REVIEW_TEST() {
        ReviewDto reviewdto = new ReviewDto(3.5, "soso", "/test/url");

        HttpHeaders requestHeaders = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntity = new HttpEntity<>(reviewdto, requestHeaders);
        ResponseEntity<Review> response = template.exchange("/api/cafes/1/menus/1/reviews", HttpMethod.POST, requestEntity, Review.class);
        log.debug("response body: {}", response.getBody().toString());
    }

    @Test
    public void EDIT_REVIEW_TEST() {
        ReviewDto reviewdto = new ReviewDto(3.5, "soso", "/test/url");

        HttpHeaders requestHeadersForPost = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntityForPost = new HttpEntity<>(reviewdto, requestHeadersForPost);
        ResponseEntity<Review> responseForPost = template.exchange("/api/cafes/1/menus/1/reviews", HttpMethod.POST, requestEntityForPost, Review.class);

        HttpHeaders requestHeadersForPut = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntityForPut = new HttpEntity<>(reviewdto, requestHeadersForPut);
        ResponseEntity<Review> responseForPut = template.exchange("/api/cafes/1/menus/1/reviews/1", HttpMethod.PUT, requestEntityForPut, Review.class);
        log.debug("response body: {}", responseForPut.getBody().toString());
    }

    @Test
    public void DELETE_REVIEW_TEST() {
        ReviewDto reviewdto = new ReviewDto(3.5, "soso", "/test/url");

        HttpHeaders requestHeadersForPost = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntityForPost = new HttpEntity<>(reviewdto, requestHeadersForPost);
        ResponseEntity<Review> responseForPost = template.exchange("/api/cafes/1/menus/1/reviews", HttpMethod.POST, requestEntityForPost, Review.class);

        HttpHeaders requestHeadersForDelete = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntityForPut = new HttpEntity<>(reviewdto, requestHeadersForDelete);
        ResponseEntity<Review> responseForDelete = template.exchange("/api/cafes/1/menus/1/reviews/1", HttpMethod.DELETE, requestEntityForPut, Review.class);
        log.debug("response body: {}", responseForDelete.getBody().toString());


    }

    //    @Test
//    public void addReviewTest_success_and_ratings_calculated_well() {
//        Review review = new Review(3.5, "soso", "/test/url");
//        ReviewDto reviewDto = ReviewDto.from(review);
//
//        HttpEntity<ReviewDto> requestEntity = new HttpEntity<>(reviewDto,buildAuthorizationHeader());
//        ResponseEntity<Menu> response = template.exchange("/api/cafes/1/menus/1/review", HttpMethod.POST, requestEntity, Menu.class);
//
//
//        ResponseEntity<String> response = template.postForEntity("/api/cafes/1/menus/1/review", reviewDto, String.class);
//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
//
//        Menu updatedMenu = template.postForObject("/api/cafes/1/menus/2/review", reviewDto, Menu.class);
//        assertThat(updatedMenu.getReviews().size(), is(1));
//
//        assertThat(updatedMenu.getReviews().get(0).getContents(), is(review.getContents()));
//        assertThat(updatedMenu.getReviews().get(0).getRatings(), is(review.getRatings()));
//        assertThat(updatedMenu.getReviews().get(0).getDrinkImgUrl(), is(review.getDrinkImgUrl()));
//        assertThat(updatedMenu.getTotalRatings(), is(3.5));
//
//        Review review2 = new Review(2.5, "bad", "/test/url2");
//        ReviewDto reviewDto2 = ReviewDto.from(review2);
//
//        updatedMenu = template.postForObject("/api/cafes/1/menus/2/review", reviewDto2, Menu.class);
//        assertThat(updatedMenu.getReviews().size(), is(2));
//        assertThat(updatedMenu.getTotalRatings(), is(3.0));
//    }


}