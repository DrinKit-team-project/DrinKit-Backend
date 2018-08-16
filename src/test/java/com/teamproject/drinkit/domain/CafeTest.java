package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.dto.CafeDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CafeTest {

    private Cafe makeCafe(String cafeName) {
        return new Cafe(cafeName);
    }

    private Cafe addThreeCategory(Cafe cafe) {
        cafe.addCategory("coffee");
        cafe.addCategory("juice");
        cafe.addCategory("smoothies");
        return cafe;
    }

    @Test
    public void registerImageURLTest() {
        Cafe cafe = addThreeCategory(makeCafe("starbucks"));
        cafe.registerImageURL("/test/URL/image/is/here");

        assertNotNull(cafe.getImageURL());
    }

    @Test
    public void addCategoryTest() {
        Cafe cafe = addThreeCategory(makeCafe("starbucks"));

        assertEquals(cafe.getCategoryList().size(), 3);
        assertEquals(cafe.getCategoryList().get(0), "coffee");
    }

    @Test
    public void makeDtoTest() {
        Cafe cafe = addThreeCategory(makeCafe("starbucks"));
        CafeDto cafeDto = cafe.makeToDto();

        assertEquals(cafeDto.getCategoryList().size(), 3);
        assertEquals(cafeDto.getName(), "starbucks");
    }
}