package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.support.test.AuthenticationTestSupporter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:/test.properties")
public class ApiAccountControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ApiAccountControllerTest.class);

    @Autowired
    private TestRestTemplate template;

    private ApiReviewControllerTest apiReviewControllerTest;

    @Before
    public void setUp() throws Exception {
        apiReviewControllerTest = new ApiReviewControllerTest();
    }

    @Test
    public void SHOW_ALL_REVIEWS_TEST() {
        apiReviewControllerTest.sendRequestForAdd(1.5, "soso", template);
        apiReviewControllerTest.sendRequestForAdd(2.5, "good", template);
        ResponseEntity<ReviewDto> response =  apiReviewControllerTest.sendRequestForAdd(3.5, "bad", template);

        Long userid = response.getBody().getWriter().getId();

        HttpEntity requestEntity = AuthenticationTestSupporter.buildRequestEntityForGet();
        ResponseEntity<Iterable<Review>> reviews = template.exchange(String.format("/api/accounts/%d/reviews", userid), HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Iterable<Review>>(){});

        reviews.getBody().forEach(r -> log.debug("review: {}", r.toString()));
    }
}