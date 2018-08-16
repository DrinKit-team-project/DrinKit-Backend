package com.teamproject.drinkit.service;

import org.junit.Test;

public class SearchServiceTest {

    @Test(expected = UnsupportedOperationException.class)
    public void test() {
        SearchService searchService = new SearchService();

        String test = "english";
        searchService.checkCharacter(test);
        test = "한글";
        searchService.checkCharacter(test);
        test = "ھسدكالېڭسقاڭلدس";
        searchService.checkCharacter(test);
    }
}
