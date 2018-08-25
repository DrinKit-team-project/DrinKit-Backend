package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.dto.ReviewDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WithMockUser(username = "drinkit", roles = { "ROLE_USER" })
public class ApiCafeControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeControllerTest.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    protected TestRestTemplate template;

    @Test
    public void cafeMainTest() {
        ResponseEntity<String> response = template.getForEntity("/api/cafes",  String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        List<Cafe> cafes = template.getForObject("/api/cafes",  List.class);
        assertThat(cafes.size(), is(5));        //import.sql 에 5개 들어가 있음.
    }

    @Test
    public void seeCafeDetailTest() {
        ResponseEntity<String> response = template.getForEntity("/api/cafes/1",  String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Cafe cafe = template.getForObject("/api/cafes/5",  Cafe.class);
        assertThat(cafe.getName(), is("koo's coffee"));     //id값 5인 카페는 koo's coffee

        log.debug("cafe is : " + cafe);
    }

    @Test
    public void seeMenuListTest() {
        ResponseEntity<String> response = template.getForEntity("/api/cafes/1/menus?category=coffee", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        List<Menu> menus = template.getForObject("/api/cafes/1/menus?category=coffee", List.class);
        assertThat(menus.size(), is(3));       //import.sql 에 3개 들어가 있음.
    }

    @Test
    public void seeMenuDetailTest() {
        ResponseEntity<String> response = template.getForEntity("/api/cafes/1/menus/1", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Menu menu = template.getForObject("/api/cafes/1/menus/1", Menu.class);
        assertThat(menu.getEnName(), is("americano"));      //id값 1인 메뉴는 아메리카노.
        log.debug("menu is : " + menu);
    }

    @Test
    public void addReviewTest_success_and_ratings_calculated_well() {
        Review review = new Review(3.5, "soso", "/test/url");
        ReviewDto reviewDto = ReviewDto.from(review);
        ResponseEntity<String> response = template.postForEntity("/api/cafes/1/menus/1/review", reviewDto, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Menu updatedMenu = template.postForObject("/api/cafes/1/menus/2/review", reviewDto, Menu.class);
        assertThat(updatedMenu.getReviews().size(), is(1));

        assertThat(updatedMenu.getReviews().get(0).getContents(), is(review.getContents()));
        assertThat(updatedMenu.getReviews().get(0).getRatings(), is(review.getRatings()));
        assertThat(updatedMenu.getReviews().get(0).getDrinkImgUrl(), is(review.getDrinkImgUrl()));
        assertThat(updatedMenu.getTotalRatings(), is(3.5));

        Review review2 = new Review(2.5, "bad", "/test/url2");
        ReviewDto reviewDto2 = ReviewDto.from(review2);

        updatedMenu = template.postForObject("/api/cafes/1/menus/2/review", reviewDto2, Menu.class);
        assertThat(updatedMenu.getReviews().size(), is(2));
        assertThat(updatedMenu.getTotalRatings(), is(3.0));
    }

}