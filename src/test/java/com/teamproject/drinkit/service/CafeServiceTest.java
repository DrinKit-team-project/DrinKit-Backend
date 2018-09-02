package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CafeServiceTest {

    private static final Logger log = LoggerFactory.getLogger(CafeServiceTest.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void test() {
        Cafe cafe = new Cafe("starbucks");
        cafe.addCategoryName("coffee");
        cafe.addCategoryName("non-coffee");
        cafe.addCategoryName("desert");
        Menu menu = new Menu("아메리카노", "americano", 237, "기본 아메리카노", "coffee");
        cafe.addMenu(menu);
        menu.registerCafe(cafe);

        menuRepository.save(menu);
        cafeRepository.save(cafe);

        Cafe cafe2 = cafeRepository.findByName("starbucks");
        log.debug(cafe2.toString());
    }
}
