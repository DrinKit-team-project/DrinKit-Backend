package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.AuthorizationException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReviewTest {
    private static final Logger log = LoggerFactory.getLogger(ReviewTest.class);


    private Review original;
    private Account writer;
    private ReviewDto newOne;

    @Before
    public void setUp() throws Exception {
        original = new Review(1L, 3.5, "original", "url");
        newOne = new ReviewDto(original.getId(), 3.0, "hi", "url", null, false, null);
        writer = new Account("jiwon", "password", "1234", UserRole.USER, "12345", SocialProviders.KAKAO, "testurl");
        original.registerWriter(writer);
    }

    @Test(expected = AuthorizationException.class)
    public void REVIEW_EDIT_DIFFERENTUSER_TEST() {
        Account other = new Account(2L,"other", "other", "other", UserRole.USER, "11111111", SocialProviders.KAKAO, "testurl");
        original.edit(other, newOne);
    }

    @Test
    public void REVIEW_EDIT_SUCCESS_TEST() {
        Review edited = original.edit(writer, newOne);
        log.debug("edited:{}", edited.toString());
        assertThat(edited.getRatings(), is(3.0));
        assertThat(edited.getContents(), is("hi"));
        assertThat(edited.getId(), is(1L));

    }

    @Test
    public void REVIEW_DELETE_SUCCESS_TEST() {
        Review deleted = original.delete(writer);
        log.debug("deleted:{}", deleted.toString());
        assertThat(deleted.isDeleted(), is(true));
    }
}