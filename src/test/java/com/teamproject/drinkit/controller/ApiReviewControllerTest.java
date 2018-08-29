package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.service.ReviewService;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiReviewControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ApiReviewControllerTest.class);

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ReviewService reviewService;

    private HttpHeaders buildRequestHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        requestHeaders.add("Authorization", "Bearer " + AuthenticationTestSupporter.requestJwtToken());
        return requestHeaders;
    }

    private ResponseEntity<ReviewDto> sendRequestForAdd(ReviewDto reviewdto) {
        HttpHeaders requestHeaders = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntity = new HttpEntity<>(reviewdto, requestHeaders);
        return template.exchange("/api/cafes/1/menus/1/reviews", HttpMethod.POST, requestEntity, ReviewDto.class);
    }

    private ResponseEntity<String> sendRequestForDelete(Long id) {
        HttpHeaders requestHeadersForDelete = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntityForDelete = new HttpEntity<>(requestHeadersForDelete);
        return template.exchange("/api/cafes/1/menus/1/reviews/" + id, HttpMethod.DELETE, requestEntityForDelete, String.class);
    }

    private ResponseEntity<ReviewDto> sendRequestForEdit(ReviewDto target) {
        HttpHeaders requestHeadersForPut = buildRequestHeader();
        HttpEntity requestEntityForPut = new HttpEntity<>(target, requestHeadersForPut);
        return template.exchange("/api/cafes/1/menus/1/reviews/", HttpMethod.PUT, requestEntityForPut, ReviewDto.class);
    }

    @Test
    public void ADD_REVIEW_TEST() {
        ReviewDto reviewdto = new ReviewDto(1.5, "soso", "/test/url");

        ResponseEntity<ReviewDto> response = sendRequestForAdd(reviewdto);
        log.debug("is deleted : {}", response.getBody().isDeleted());
        log.debug("response body: {}", response.getBody().toString());
    }

    @Test
    public void EDIT_REVIEW_TEST() {
        ReviewDto reviewdto = new ReviewDto(2.5, "soso", "/test/url");

        ResponseEntity<ReviewDto> responseForPost = sendRequestForAdd(reviewdto);

        Long id = responseForPost.getBody().getId();
        boolean deleted = responseForPost.getBody().isDeleted();
        ReviewDto target = new ReviewDto(id, 3.0, "changed", "url", deleted);

        ResponseEntity<ReviewDto> responseForPut = sendRequestForEdit(target);
        log.debug("response body: {}", responseForPut.getBody().toString());
    }

    @Test
    public void DELETE_REVIEW_TEST() {
        ReviewDto reviewdto = new ReviewDto(5.5, "soso", "/test/url");

        ResponseEntity<ReviewDto> responseForPost = sendRequestForAdd(reviewdto);

        Long id = responseForPost.getBody().getId();

        ResponseEntity<String> responseForDelete = sendRequestForDelete(id);
        log.debug("status code : {}", responseForDelete.getStatusCode());
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