package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.Menu;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

//    @Test
//    public void checkCharacterTest_success() {
//        SearchService searchService = new SearchService();
//        Iterable<Menu> menus = searchService.checkCharacter("americano");
//        for (Menu menu : menus) {
//            log.debug("menu : " + menu);
//        }
//        searchService.checkCharacter("한글");
//    }

    @Test(expected = NullPointerException.class)
    public void checkCharacterTest_fail_nullpointerException() {
        SearchService searchService = new SearchService();
        searchService.checkCharacter("");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkCharacterTest_fail_not_supported_language() {
        SearchService searchService = new SearchService();
        searchService.checkCharacter("ھسدكالېڭسقاڭلدس");
    }

}
