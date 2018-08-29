package com.teamproject.drinkit.domain;

import java.util.ArrayList;
import java.util.List;

public class FeaturedMenus {

    private List<Menu> menuList;

    public FeaturedMenus() {
        this.menuList = new ArrayList<>();
    }

    public List<Menu> addMenu(Menu menu) {
        this.menuList.add(menu);
        return menuList;
    }

    public List<Menu> getMenus() {
        return this.menuList;
    }
}
