package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.Menu;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/test.properties")
public class ApiReviewControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ApiReviewControllerTest.class);

    @Autowired
    private TestRestTemplate template;

    private HttpHeaders buildRequestHeader() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer " + AuthenticationTestSupporter.requestJwtToken());
        return requestHeaders;
    }

    private MultiValueMap<String, Object> makeRequestParams(double ratings, String contents) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("ratings", ratings);
        params.add("contents", contents);
        return params;
    }

    private MultiValueMap<String, Object> makeRequestParams(Long id, double ratings, String contents) {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("id", id);
        params.add("ratings", ratings);
        params.add("contents", contents);
        return params;
    }

    public ResponseEntity<ReviewDto> sendRequestForAdd(double ratings, String contents, TestRestTemplate template) {
        HttpHeaders requestHeaders = buildRequestHeader();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(makeRequestParams(ratings, contents), requestHeaders);
        return template.exchange("/api/menus/1/reviews", HttpMethod.POST, requestEntity, ReviewDto.class);
    }

    private ResponseEntity<ReviewDto> sendRequestForEdit(Long id, double ratings, String contents, TestRestTemplate template) {
        HttpHeaders requestHeadersForPut = buildRequestHeader();
        HttpEntity requestEntityForPut = new HttpEntity<>(makeRequestParams(id, ratings, contents), requestHeadersForPut);
        return template.exchange("/api/menus/1/reviews/", HttpMethod.POST, requestEntityForPut, ReviewDto.class);
    }

    private ResponseEntity<String> sendRequestForDelete(Long id) {
        HttpHeaders requestHeadersForDelete = buildRequestHeader();
        HttpEntity<ReviewDto> requestEntityForDelete = new HttpEntity<>(requestHeadersForDelete);
        return template.exchange("/api/menus/1/reviews/" + id, HttpMethod.DELETE, requestEntityForDelete, String.class);
    }

    @Test
    public void ADD_REVIEW_TEST() {
        ResponseEntity<ReviewDto> response = sendRequestForAdd(3.5, "soso", template);
        log.debug("response body: {}", response.getBody().toString());
        log.debug("account id : {}", response.getBody().getWriter().getId());
    }

    @Test
    public void EDIT_REVIEW_TEST() {
        ResponseEntity<ReviewDto> responseForPost = sendRequestForAdd(2.5, "soso", template);
        Long id = responseForPost.getBody().getId();
        ResponseEntity<ReviewDto> responseForPut = sendRequestForEdit(id, 1.9, "changed", template);
        log.debug("response body: {}", responseForPut.getBody().toString());
    }

    @Test
    public void DELETE_REVIEW_TEST() {
        ResponseEntity<ReviewDto> responseForPost = sendRequestForAdd(2.5, "soso", template);

        Long id = responseForPost.getBody().getId();

        ResponseEntity<String> responseForDelete = sendRequestForDelete(id);
        log.debug("status code : {}", responseForDelete.getStatusCode());
    }
}