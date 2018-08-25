package com.teamproject.drinkit.domain;

import java.util.ArrayList;
import java.util.List;

public class NewMenus implements FeaturedMenus {

    private List<Menu> menuList;

    public NewMenus() {
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
