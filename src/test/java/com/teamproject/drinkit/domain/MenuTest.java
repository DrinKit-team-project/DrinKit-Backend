package com.teamproject.drinkit.domain;

import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class MenuTest {

    private Menu makeMenu() {
        Menu newMenu = new Menu("아메리카노", "americano", 150, "Not good.");
        return newMenu;
    }

    @Test
    public void pricePerSizeTest() {
        Menu newMenu = makeMenu();
        assertEquals(newMenu.getPricePerSizes().size(), 0);

        PricePerSize testPricePerSize = new PricePerSize("small", 2000);
        newMenu.addPricePerSize(testPricePerSize);

        assertEquals(newMenu.getPricePerSizes().size(), 1);
        assertEquals(newMenu.getPricePerSizes().get(0), testPricePerSize);
    }

    @Test
    public void calculateTest_menu_exist() {
        Menu newMenu = makeMenu();
        Review reviewOne = new Review(4.5, "맛있습니다.");
        Review reviewTwo = new Review(0.5, "맛없습니다.");
        Review reviewThree = new Review(3, "그냥그래요.");

        newMenu.addReview(reviewOne);
        newMenu.addReview(reviewTwo);
        newMenu.addReview(reviewThree);

        assertEquals(newMenu.getReviews().size(), 3);
        assertEquals(2.6, newMenu.getTotalRatings(), 0.1);
    }

    @Test
    public void calculateTest_menu_not_exist() {
        Menu newMenu = makeMenu();

        assertEquals(newMenu.getReviews().size(), 0);
        assertEquals(0.0, newMenu.getTotalRatings(), 0.1);
    }

    @Test
    public void registerCafeTest() {
        Menu newMenu = makeMenu();
        Cafe cafe = new Cafe("starbucks", "imageurl", "categories");

        newMenu.registerCafe(cafe);
        assertEquals(newMenu.getCafe(), cafe);
    }

    @Test
    public void editDescriptionTest() {
        Menu newMenu = makeMenu();
        assertEquals(newMenu.getDescription(), "Not good.");

        newMenu.editDescription("edit");

        assertEquals(newMenu.getDescription(), "edit");
    }

}
