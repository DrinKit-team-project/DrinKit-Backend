package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/search")
@PreAuthorize("hasRole('ROLE_USER')")
public class ApiSearchController {

    private static final Logger log = LoggerFactory.getLogger(ApiSearchController.class);

    @Resource(name="searchService")
    private SearchService searchService;

    @Autowired
    private MenuRepository menuRepository;

//    @GetMapping("")
//    public Model searchMain(Model model) {
//        model.addAttribute("newMenus", searchService.findNewMenu());
//        return model;
//    }

    @GetMapping("")
    public Iterable<Menu> searchTopReviewedMenu(@PageableDefault(sort = { "total_ratings" }, direction = Sort.Direction.ASC, size = 5) Pageable pageable) {
        Iterable<Menu> topMenus = menuRepository.findAll(pageable);
        for (Menu menu : topMenus) {
            log.debug("top menu : " + menu);
        }
        return topMenus;
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
