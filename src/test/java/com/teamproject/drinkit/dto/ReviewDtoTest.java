package com.teamproject.drinkit.dto;

import com.teamproject.drinkit.domain.Review;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviewDtoTest {
    private static final Logger log = LoggerFactory.getLogger(ReviewDtoTest.class);

    private Review review;

    @Before
    public void setUp() throws Exception {
        review = new Review(1L, 3.5, "soso");
    }

    @Test
    public void REVIEW_TO_DTO_TEST() {
        ReviewDto dto = ReviewDto.from(review);
        log.debug("dto : {}", dto.toString());
    }
}