package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.Menu;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:/test.properties")
public class SearchServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Test(expected = NullPointerException.class)
    public void checkCharacterTest_fail_nullpointerException() {
        SearchService searchService = new SearchService();
        searchService.findWithKeyword("");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void checkCharacterTest_fail_not_supported_language() {
        SearchService searchService = new SearchService();
        searchService.findWithKeyword("ھسدكالېڭسقاڭلدس");
    }

}
