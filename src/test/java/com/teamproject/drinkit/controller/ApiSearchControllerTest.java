package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiSearchControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiSearchControllerTest.class);

    @Autowired
    protected TestRestTemplate template;

//    @Test
//    public void test() {
//        ResponseEntity<String> response = template.getForEntity("/search", String.class);
//        assertThat(response.getStatusCode(), is(HttpStatus.OK));
//    }

    @Test
    public void searchTest_success() {
        ResponseEntity<String> response = template.postForEntity("/search","americano" , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        ResponseEntity<String> response2 = template.postForEntity("/search","라떼" , String.class);
        assertThat(response2.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void searchTest_fail_no_such_menu() {
        ResponseEntity<String> response = template.postForEntity("/search","notCoffeeHeHe" , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity<String> response2 = template.postForEntity("/search","한글메뉴없는메뉴" , String.class);
        assertThat(response2.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void searchTest_fail_input_is_null() {
        ResponseEntity<String> response = template.postForEntity("/search",null , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void searchTest_fail_input_language_not_supported() {
        ResponseEntity<String> response = template.postForEntity("/search","ھسدكالېڭسقاڭلدس" , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
