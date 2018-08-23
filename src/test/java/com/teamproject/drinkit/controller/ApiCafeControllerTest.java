package com.teamproject.drinkit.controller;

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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiCafeControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeControllerTest.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    protected TestRestTemplate template;

//    @Before
//    public void makeCafes() {
//        Cafe cafe1 = new Cafe("starbucks");
//        Cafe cafe2 = new Cafe("tom&toms");
//        Cafe cafe3 = new Cafe("ediya");
//        cafeRepository.save(cafe1);
//        cafeRepository.save(cafe2);
//        cafeRepository.save(cafe3);
//    }

    @Test
    public void cafeMainTest() {
        ResponseEntity<String> response = template.getForEntity("/cafes",  String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void seeCafeDetailTest() {
        ResponseEntity<String> response = template.getForEntity("/cafes/1",  String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void seeMenuListTest() {
        ResponseEntity<String> response = template.getForEntity("/cafes/1/menus?category=coffee", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        List<Menu> responseObject = template.getForObject("/cafes/1/menus?category=coffee", List.class);
        assertThat(responseObject.size(), is(3));       //import.sql 에 3개 들어가 있음.
    }

    @Test
    public void seeMenuDetailTest() {
        ResponseEntity<String> response = template.getForEntity("/cafes/1/menus/1", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Menu responseObject = template.getForObject("/cafes/1/menus/1", Menu.class);
        log.debug("test : " + responseObject);
    }

    @Test
    public void addReviewTest() {
        Review review = new Review(3.5, "soso", "/test/url");
        ReviewDto reviewDto = ReviewDto.from(review);
        ResponseEntity<String> response = template.postForEntity("/cafes/1/menus/1/review", reviewDto, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        Menu updatedMenu = template.postForObject("/cafes/1/menus/2/review", reviewDto, Menu.class);
        log.debug("test : " + updatedMenu);
        assertThat(updatedMenu.getReviews().size(), is(1));
    }

}