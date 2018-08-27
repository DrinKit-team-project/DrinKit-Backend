package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Tag;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiSearchControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ApiSearchControllerTest.class);

    @Autowired
    protected TestRestTemplate template;

    @Test
    public void searchTest_success() {
        ResponseEntity<String> response = template.postForEntity("/search","americano" , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        ResponseEntity<String> response2 = template.postForEntity("/search","라떼" , String.class);
        assertThat(response2.getStatusCode(), is(HttpStatus.OK));

        ArrayList<Menu> menus = template.postForObject("/search","라떼" , ArrayList.class);
        log.debug("menu is : " + menus.get(0));
    }

    @Test
    public void searchTest_PlusTagCount_success() {
        Tag tag = template.getForObject("/search/tagTest/4", Tag.class);
        int tagCountNum = tag.getSearchCount();
        int predictNum = tagCountNum + 1;

        ArrayList<Menu> menus = template.postForObject("/search","라떼" , ArrayList.class);
        log.debug("menu is : " + menus.get(0));

        tag = template.getForObject("/search/tagTest/4", Tag.class);
        assertThat(tag.getSearchCount(), is(predictNum));
    }

    @Test
    public void searchTest_fail_no_such_menu() {
        ResponseEntity<String> response = template.postForEntity("/search","notCoffeeHeHe" , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));

        ResponseEntity<String> response2 = template.postForEntity("/search","한글메뉴없는메뉴" , String.class);
        assertThat(response2.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void searchTest_fail_input_language_not_supported() {
        ResponseEntity<String> response = template.postForEntity("/search","ھسدكالېڭسقاڭلدس" , String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void searchNewMenuTest_success() {
        ResponseEntity<String> response = template.getForEntity("/search/newMenus", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        List<Menu> newMenus = template.getForObject("/search/newMenus", List.class);
        assertThat(newMenus.size(), is(3));
//        for (int i = 0; i < newMenus.size(); i++) {
//            log.debug("newMenu " + i + " : " + newMenus.get(i));
//        }
    }

    @Test
    public void searchTopReviewedMenuTest_success() {
        ResponseEntity<String> response = template.getForEntity("/search/topReviewed", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        List<Menu> topMenus = template.getForObject("/search/topReviewed", List.class);
        assertThat(topMenus.size(), is(3));
//        for (int i = 0; i < topMenus.size(); i++) {
//            log.debug("topMenus " + i + " : " + topMenus.get(i));
//        }
    }

    @Test
    public void getSuggestedTagsTest_success() {
        ResponseEntity<String> response = template.getForEntity("/search/suggestedTags", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        List<Tag> suggestedTags = template.getForObject("/search/suggestedTags", List.class);
        assertThat(suggestedTags.size(), is(4));
        for (int i = 0; i < suggestedTags.size(); i++) {
            log.debug("suggestedTags " + i + " : " + suggestedTags.get(i));
        }
    }

}
