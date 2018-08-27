package com.teamproject.drinkit.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiReviewControllerTest {

    @Autowired
    private TestRestTemplate template;

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