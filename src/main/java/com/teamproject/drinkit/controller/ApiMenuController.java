package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.CafeService;
import com.teamproject.drinkit.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cafes/{cafeId}/menus")
public class ApiMenuController {

    private static final Logger log = LoggerFactory.getLogger(ApiMenuController.class);

    @Resource(name = "menuService")
    private MenuService menuService;

    @GetMapping("")
    public Iterable<Menu> seeMenuList(@PathVariable Long cafeId, @RequestParam("category") String categoryName) throws NoSuchMenuException {
        return menuService.findMenuList(cafeId, categoryName);
    }

    @PostMapping("")
    public void addMenu(@PathVariable Long cafeId, MenuDto menuDto) {
        Menu newMenu = menuService.addMenu(cafeId, menuDto);
        log.debug(newMenu.toString());
    }

    @GetMapping("/{menuId}")
    public Menu seeMenuDetail(@PathVariable Long cafeId, @PathVariable Long menuId) {
        log.debug("cafeId is " + cafeId + ", menuId is " + menuId);
        Menu menu = menuService.findMenu(cafeId, menuId);
        return menu;
    }
}
