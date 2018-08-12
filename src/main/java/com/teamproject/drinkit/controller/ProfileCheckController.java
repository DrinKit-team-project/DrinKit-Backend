package com.teamproject.drinkit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ProfileCheckController {

    @Autowired
    private Environment environment;

    @GetMapping("/profile")
    public String getActivatedProfile(){
        return Arrays.stream(environment.getActiveProfiles()).findFirst().orElse("");
    }

}
