package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.dto.CafeDto;
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
    public Menu seeMenuDetail(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName) {
        return cafeService.findMenu(cafeName, menuName);
    }

    @PostMapping("/{cafeName}/menu/{menuName}/addReview")
    public void addReview(@Valid @PathVariable String cafeName, @Valid @PathVariable String menuName) {

    }

}
