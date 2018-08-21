package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.CafeDto;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.service.CafeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cafes")
public class ApiCafeController {
    @Resource(name = "cafeService")
    private CafeService cafeService;

    @GetMapping("")
    public List<Cafe> main() {
        return cafeService.findAllCafe();
    }

//    @GetMapping("/")
//    public CafeDto seeCafeDetail(@RequestParam("cafeName") String cafeName) {
//        return cafeService.findCafe(cafeName).makeToDto();
//    }
    @GetMapping("/{cafeId}")
    public CafeDto seeCafeDetail(@Valid @PathVariable Long cafeId) throws NoSuchMenuException {
        return cafeService.findCafe(cafeId).makeToDto();
    }

//    @GetMapping("/{cafeName}")
//    public Iterable<Menu> seeMenuList(@Valid @PathVariable String cafeName, @RequestParam("categoryName") String categoryName) {
//        return cafeService.findMenuList(cafeName, categoryName);
//    }
    @GetMapping("/{cafeId}/menus")
    public Iterable<Menu> seeMenuList(@Valid @PathVariable Long cafeId, @RequestParam("category") String categoryName) throws NoSuchMenuException {
           return cafeService.findMenuList(cafeId, categoryName);
    }

//    @GetMapping("?cafeName={cafeName}&menuName={menuName}")
//    public MenuDto seeMenuDetail(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName) {
//        Menu menu = cafeService.findMenu(cafeName, menuName);
//        return cafeService.makeMenuDto(menu);
//    }

//    @GetMapping("/{cafeName}/{categoryName}")
//    public MenuDto seeMenuDetail(@Valid @PathVariable String cafeName, @Valid @PathVariable String categoryName, @RequestParam("menuName") String menuName) {
//        Menu menu = cafeService.findMenu(cafeName, menuName);
//        return cafeService.makeMenuDto(menu);
//    }
    @GetMapping("/{cafeId}/menus/{menuId}")
    public MenuDto seeMenuDetail(@Valid @PathVariable Long cafeId, @Valid @PathVariable Long menuId) {
        Menu menu = cafeService.findMenu(cafeId, menuId);
        return cafeService.makeMenuDto(menu);
    }

    @PostMapping("/{cafeId}/menus/{menuId}/review")
    public void addReview(@Valid @PathVariable Long cafeId, @Valid @PathVariable Long menuId, @RequestBody ReviewDto reviewDto) {
        Review newReview = Review.from(reviewDto);
        Menu menu = cafeService.findMenu(cafeId, menuId);
        cafeService.addReview(newReview, menu);
    }

    @PutMapping("/{cafeName}/menus/{menuName}/review")
    public void editReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 수정할 수 있지 않은가.
    }

    @DeleteMapping("/{cafeName}/menus/{menuName}/review")
    public void deleteReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 삭제할 수 있지 않은가.
    }


}
