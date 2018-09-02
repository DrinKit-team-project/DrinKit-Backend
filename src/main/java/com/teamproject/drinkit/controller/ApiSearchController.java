package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.FeaturedMenus;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.domain.Tag;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

    @PostMapping("")
    public Iterable<Menu> searchMenu(@Valid @RequestBody String searchKeyWord) throws UnsupportedOperationException, NoSuchMenuException{
        log.debug("search keyword is : " + searchKeyWord);

        Iterable<Menu> menus;
        try {
            menus = searchService.findWithKeyword(searchKeyWord);
        }catch (NoSuchMenuException e) {
            throw new NoSuchMenuException("그런거 없 뜸.");
        }
        log.debug("!!! : " + menus.toString());

        return menus;
    }

    @GetMapping("/newMenus")
    public List<Menu> searchNewMenu() {
        log.debug("search new menu in.");
        FeaturedMenus newMenus = searchService.findNewMenus();
        return newMenus.getMenus();
    }

    @GetMapping("/topReviewed")
    public List<Menu> searchTopReviewedMenu() {
        log.debug("search top reviewed menu in.");
        FeaturedMenus topMenus = searchService.findTopReviewedMenus();
        return topMenus.getMenus();
    }

    @GetMapping("/suggestedTags")
    public List<Tag> getSuggestedTags() {
        log.debug("get suggested tags in.");
        return searchService.getSuggestedTags();
    }

}
