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
@RequestMapping("/api/cafes")
public class ApiCafeController {

    private static final Logger log = LoggerFactory.getLogger(ApiCafeController.class);

    @Resource(name = "cafeService")
    private CafeService cafeService;

    @GetMapping("")
    public List<Cafe> main() {
        return cafeService.findAllCafe();
    }

    @GetMapping("/{cafeId}")
    public CafeDto seeCafeDetail(@PathVariable Long cafeId) throws NoSuchMenuException {
        return cafeService.findCafe(cafeId).makeToDto();
    }
}
