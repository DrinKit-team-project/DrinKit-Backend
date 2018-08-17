package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.service.CafeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiCafeControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeControllerTest.class);

    @Autowired
    private CafeService cafeService;

    @Autowired
    protected TestRestTemplate template;

    @Before
    public void makeCafes() {
        Cafe cafe1 = new Cafe("starbucks");
        Cafe cafe2 = new Cafe("tom&toms");
        Cafe cafe3 = new Cafe("ediya");
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
    }

    @Test
    public void main() {
        ResponseEntity<String> response = template.getForEntity("/cafes",  String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}