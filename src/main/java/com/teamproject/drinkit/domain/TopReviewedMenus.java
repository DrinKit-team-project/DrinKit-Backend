package com.teamproject.drinkit.domain;

import java.util.ArrayList;
import java.util.List;

public class TopReviewedMenus implements FeaturedMenus {

    private List<Menu> menuList;

    public TopReviewedMenus() {
        this.menuList = new ArrayList<>();
    }

    @Override
    public List<Menu> addMenu(Menu menu) {
        this.menuList.add(menu);
        return menuList;
    }

    @Override
    public List<Menu> getMenus() {
        return this.menuList;
    }
}
