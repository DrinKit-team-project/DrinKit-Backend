package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.dto.CafeDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.*;

public class CafeTest {

    private Cafe makeCafe(String cafeName) {
        return new Cafe(cafeName);
    }

    @Test
    public void registerImageURLTest() {
        Cafe cafe = makeCafe("starbucks");
        cafe.registerImageURL("/test/URL/image/is/here");

        assertNotNull(cafe.getImageURL());
    }

    @Test
    public void makeDtoTest() {
        Cafe cafe = makeCafe("starbucks");
        CafeDto cafeDto = cafe.makeToDto();

        assertEquals(cafeDto.getName(), "starbucks");
    }
}