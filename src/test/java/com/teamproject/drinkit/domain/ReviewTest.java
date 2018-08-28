package com.teamproject.drinkit.domain;

import com.teamproject.drinkit.exception.AuthorizationException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReviewTest {

    private Review original;
    private Account writer;
    private Review newOne;

    @Before
    public void setUp() throws Exception {
        original = new Review(1L, 3.5, "original", "url");

        writer = new Account("jiwon", "password", "1234", UserRole.USER, "12345", SocialProviders.KAKAO, "testurl");
        original.registerWriter(writer);
    }

    @Test(expected = AuthorizationException.class)
    public void REVIEW_EDIT_DIFFERENTUSER_TEST() {
        Account other = new Account(2L,"other", "other", "other", UserRole.USER, "11111111", SocialProviders.KAKAO, "testurl");
        original.edit(other, 5, "new");
    }

    @Test
    public void REVIEW_EDIT_SUCCESS_TEST() {
        Review newOne = original.edit(writer, 5.0, "new");
        assertThat(newOne.getRatings(), is(5.0));
        assertThat(newOne.getContents(), is("new"));
    }
}