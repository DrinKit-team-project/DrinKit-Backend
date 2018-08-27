package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.support.test.AuthenticationTestSupporter;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiMenuControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiMenuControllerTest.class);

    @Autowired
    private TestRestTemplate template;

    @Test
    public void seeMenuListTest() {
        HttpEntity requestEntity = AuthenticationTestSupporter.buildRequestEntity();
        ResponseEntity<List<Menu>> response = template.exchange("/api/cafes/1/menus?category=coffee", HttpMethod.GET, requestEntity, new ParameterizedTypeReference<List<Menu>>(){});

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(3));
    }

    @Test
    public void seeMenuDetailTest() {
        HttpEntity requestEntity = AuthenticationTestSupporter.buildRequestEntity();
        ResponseEntity<Menu> response = template.exchange("/api/cafes/1/menus/1", HttpMethod.GET, requestEntity, Menu.class);
        String menu = response.getBody().getEnName();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(menu, is("americano"));
    }

}