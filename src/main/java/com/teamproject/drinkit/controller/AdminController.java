package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.service.CafeService;
import com.teamproject.drinkit.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CafeService cafeService;

    @Autowired
    private MenuService menuService;

    @GetMapping("/cafes")
    public String adminCafe(Model model) {
        log.debug("in!!!");
        model.addAttribute("cafes", cafeService.findAll());
        return "adminPageCafe";
    }

    @GetMapping("/menus")
    public String adminMenu(Model model) {
        log.debug("in!!");
        model.addAttribute("menus", menuService.findAll());
        model.addAttribute("cafes", cafeService.findAll());
        return "adminPageMenu";
    }

}
