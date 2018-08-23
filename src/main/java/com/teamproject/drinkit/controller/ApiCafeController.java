package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.CafeDto;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.CafeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cafes")
public class ApiCafeController {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeController.class);

    @Resource(name = "cafeService")
    private CafeService cafeService;

    @GetMapping("")
    public List<Cafe> main() {
        return cafeService.findAllCafe();
    }

    @GetMapping("/{cafeId}")
    public CafeDto seeCafeDetail(@Valid @PathVariable Long cafeId) throws NoSuchMenuException {
        return cafeService.findCafe(cafeId).makeToDto();
    }

    @GetMapping("/{cafeId}/menus")
    public Iterable<Menu> seeMenuList(@Valid @PathVariable Long cafeId, @RequestParam("category") String categoryName) throws NoSuchMenuException {
           return cafeService.findMenuList(cafeId, categoryName);
    }

//    @GetMapping("/{cafeId}/menus/{menuId}")
//    public MenuDto seeMenuDetail(@Valid @PathVariable Long cafeId, @Valid @PathVariable Long menuId) {
//        log.debug("see menu Detail controller in.");
//        log.debug("cafeId is " + cafeId + ", menuId is " + menuId);
//        Menu menu = cafeService.findMenu(cafeId, menuId);
//        return cafeService.makeMenuDto(menu);
//    }
    @GetMapping("/{cafeId}/menus/{menuId}")
    public Menu seeMenuDetail(@Valid @PathVariable Long cafeId, @Valid @PathVariable Long menuId) {
        log.debug("see menu Detail controller in.");
        log.debug("cafeId is " + cafeId + ", menuId is " + menuId);
        Menu menu = cafeService.findMenu(cafeId, menuId);
        return menu;
    }

    @PostMapping("/{cafeId}/menus/{menuId}/review")     //return type 등 추가 구현 필요.
    public Menu addReview(@Valid @PathVariable Long cafeId, @Valid @PathVariable Long menuId, @RequestBody ReviewDto reviewDto) {
        Review newReview = Review.from(reviewDto);
        Menu menu = cafeService.findMenu(cafeId, menuId);
        Menu updatedMenu = cafeService.addReview(newReview, menu);
        log.debug("controller : " + updatedMenu.toString());
        return updatedMenu;
    }

    @PutMapping("/{cafeName}/menus/{menuName}/review")
    public void editReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 수정할 수 있지 않은가.
    }

    @DeleteMapping("/{cafeName}/menus/{menuName}/review")
    public void deleteReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 삭제할 수 있지 않은가.
    }

//    ---

    @PostMapping("/")
    public void addCafe(String name) {
        Cafe newCafe = cafeService.addCafe(name);
        log.debug(newCafe.toString());
    }

    @PostMapping("/{cafeId}/menus")
    public void addMenu(@Valid @PathVariable Long cafeId, MenuDto menuDto) {
        Menu newMenu = cafeService.addMenu(cafeId, menuDto);
        log.debug(newMenu.toString());
    }

    @GetMapping("/test")
    public void testController() {
        Iterable<Cafe> cafes = cafeService.findAllCafe();
        Iterable<Menu> menus = cafeService.findAllMenu();

        for (Cafe cafe : cafes) {
            log.debug("cafe list : " + cafe);
        }
        for (Menu menu : menus) {
            log.debug("menu list : " + menu);
        }
    }

}
