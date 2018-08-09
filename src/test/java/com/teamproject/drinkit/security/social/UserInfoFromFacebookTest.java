package com.teamproject.drinkit.security.social;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserInfoFromFacebookTest {

    private UserInfoFromFacebook userInfoFromFacebook;
    private static final Logger log = LoggerFactory.getLogger(UserInfoFromFacebookTest.class);


    @Before
    public void setUp() throws Exception {
        userInfoFromFacebook = new UserInfoFromFacebook();
    }

    @Test
    public void PROFILE_HREF_JSON_PARSE_TEST() {
        String sample = "{\n" +
                "  \"id\": \"704798269891295\",\n" +
                "  \"name\": \"Jiwon Bae\",\n" +
                "  \"email\": \"jwb8705@gmail.com\",\n" +
                "  \"picture\": {\n" +
                "    \"data\": {\n" +
                "      \"height\": 50,\n" +
                "      \"is_silhouette\": false,\n" +
                "      \"url\": \"https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=704798269891295&height=50&width=50&ext=1536286463&hash=AeTu17KZv_qhc6A6\",\n" +
                "      \"width\": 50\n" +
                "    }\n" +
                "  }\n" +
                "}";
        String url = "https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=704798269891295&height=50&width=50&ext=1536286463&hash=AeTu17KZv_qhc6A6";
    }
}