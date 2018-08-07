package com.teamproject.drinkit.domain;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class UserRoleTest {
    private static final Logger log = LoggerFactory.getLogger(UserRoleTest.class);


    @Test
    public void GET_ROLE_NAME_TEST() {
        log.debug("userRole:{}", UserRole.fromRoleName("ROLE_USER"));
    }
}