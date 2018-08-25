package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
<<<<<<< HEAD
=======
import com.teamproject.drinkit.service.CafeService;
>>>>>>> 18dfa423579d2be433f3a56e1936ab5f1b101559
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
<<<<<<< HEAD
=======
import org.springframework.test.context.ActiveProfiles;
>>>>>>> 18dfa423579d2be433f3a56e1936ab5f1b101559
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiCafeControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeControllerTest.class);

    @Autowired
<<<<<<< HEAD
    private CafeRepository cafeRepository;
=======
    private CafeService cafeService;
>>>>>>> 18dfa423579d2be433f3a56e1936ab5f1b101559

    @Autowired
    protected TestRestTemplate template;

    @Before
    public void makeCafes() {
        Cafe cafe1 = new Cafe("starbucks");
        Cafe cafe2 = new Cafe("tom&toms");
        Cafe cafe3 = new Cafe("ediya");
<<<<<<< HEAD
        cafeRepository.save(cafe1);
        cafeRepository.save(cafe2);
        cafeRepository.save(cafe3);
=======
        cafeService.save(cafe1);
        cafeService.save(cafe2);
        cafeService.save(cafe3);
>>>>>>> 18dfa423579d2be433f3a56e1936ab5f1b101559
    }

    @Test
    public void main() {
        ResponseEntity<String> response = template.getForEntity("/cafes",  String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}