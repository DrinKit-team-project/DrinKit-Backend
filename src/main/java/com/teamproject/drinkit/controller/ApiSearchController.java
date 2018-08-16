package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/search")
public class ApiSearchController {
    @Resource(name="searchService")
    private SearchService searchService;

    @GetMapping("")
    public void searchMain() {

    }
}
