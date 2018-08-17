package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/search")
public class ApiSearchController {

    private static final Logger log = LoggerFactory.getLogger(ApiSearchController.class);

    @Resource(name="searchService")
    private SearchService searchService;

//    @GetMapping("")
//    public Model searchMain(Model model) {
//        model.addAttribute("newMenus", searchService.findNewMenu());
//        return model;
//    }

    @PostMapping("")
    public Iterable<Menu> searchMenu(@RequestBody String searchKeyWord) throws NullPointerException, UnsupportedOperationException, NoSuchMenuException{
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
