package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.CafeDto;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.service.CafeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cafes")
public class ApiCafeController {
    @Resource(name = "cafeService")
    private CafeService cafeService;

    @GetMapping("")
    public List<Cafe> main() {
        return cafeService.findAllCafe();
    }

    @GetMapping("?cafeName={cafeName}")
    public CafeDto seeCafeDetail(@Valid @PathVariable String cafeName) {
        return cafeService.findCafe(cafeName).makeToDto();
    }

    @GetMapping("?cafeName={cafeName}&categoryName={categoryName}")
    public Iterable<Menu> seeMenuList(@Valid @PathVariable String cafeName, @Valid @PathVariable String categoryName) {
        return cafeService.findMenuList(cafeName, categoryName);
    }

    @GetMapping("?cafeName={cafeName}&menuName={menuName}")
    public MenuDto seeMenuDetail(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName) {
        Menu menu = cafeService.findMenu(cafeName, menuName);
        return cafeService.makeMenuDto(menu);
    }

    @PostMapping("/{cafeName}/menu/{menuName}/review")
    public void addReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        Review newReview = Review.from(reviewDto);
        Menu menu = cafeService.findMenu(cafeName, menuName);
        cafeService.addReview(newReview, menu);
    }

    @PutMapping("/{cafeName}/menu/{menuName}/review")
    public void editReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 수정할 수 있지 않은가.
    }

    @DeleteMapping("/{cafeName}/menu/{menuName}/review")
    public void deleteReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName, @RequestBody ReviewDto reviewDto) {
        //user 와 review 에 연관관계가 맺어져야 사이에 key를 가지고 삭제할 수 있지 않은가.
    }


}
