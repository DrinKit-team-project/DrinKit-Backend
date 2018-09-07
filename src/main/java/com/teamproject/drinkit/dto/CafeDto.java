package com.teamproject.drinkit.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class CafeDto {
    private String name;
    private String imageURL;

    public CafeDto(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }
}
