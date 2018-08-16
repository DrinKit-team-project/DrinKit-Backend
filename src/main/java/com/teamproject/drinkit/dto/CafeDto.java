package com.teamproject.drinkit.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeDto {
    private String name;
    private String imageURL;
    private List<String> categoryList = new ArrayList<>();

    public CafeDto(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }

    public void addCategoty(String expectedCategoryName) {
        categoryList.add(expectedCategoryName);
    }
}
