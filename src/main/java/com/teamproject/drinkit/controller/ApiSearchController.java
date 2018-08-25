package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.domain.NewMenus;
import com.teamproject.drinkit.domain.TopReviewedMenus;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/search")
public class ApiSearchController {

    private static final Logger log = LoggerFactory.getLogger(ApiSearchController.class);

    @Resource(name="searchService")
    private SearchService searchService;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/newMenus")
    public List<Menu> searchNewMenu() {
        log.debug("search new menu in.");
        NewMenus newMenus = searchService.findNewMenus();
        return newMenus.getMenus();
    }

    @GetMapping("/topReviewed")
    public List<Menu> searchTopReviewedMenu() {
        log.debug("search top reviewed menu in.");
        TopReviewedMenus topMenus = searchService.findTopReviewedMenus();
        return topMenus.getMenus();
    }

    @PostMapping("")
    public Iterable<Menu> searchMenu(@Valid @RequestBody String searchKeyWord) throws UnsupportedOperationException, NoSuchMenuException{
        log.debug("search keyword is : " + searchKeyWord);

        Iterable<Menu> menus;
        try {
            menus = searchService.checkCharacter(searchKeyWord);
        }catch (NoSuchMenuException e) {
            throw new NoSuchMenuException("그런거 없 뜸.");
        }
        log.debug("!!! : " + menus.toString());

        return searchService.checkCharacter(searchKeyWord);
    }
}
