package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.support.test.AuthenticationTestSupporter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.teamproject.drinkit.support.test.AuthenticationTestSupporter.buildRequestEntityForGet;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiCafeControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeControllerTest.class);

    @Autowired
    protected TestRestTemplate template;

    @Test
    public void cafeMainTest() {
        HttpEntity requestEntity = AuthenticationTestSupporter.buildRequestEntityForGet();
        ResponseEntity<List<Cafe>> response = template.exchange("/api/cafes", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Cafe>>(){});

        assertThat(response.getBody().size(), is(5));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void seeCafeDetailTest() {
        HttpEntity requestEntity = AuthenticationTestSupporter.buildRequestEntityForGet();
        ResponseEntity<Cafe> response = template.exchange("/api/cafes/1", HttpMethod.GET, requestEntity, Cafe.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        String cafeName = response.getBody().getName();
        assertThat(cafeName, is("starbucks"));     //id값 5인 카페는 koo's coffee

        log.debug("cafe is : " + cafeName);
    }

}