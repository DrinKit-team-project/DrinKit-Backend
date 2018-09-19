package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

    @Resource(name = "adminService")
    private AdminService adminService;

    @PostMapping(value = "/cafes", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Cafe> createCafe(@RequestBody String nameAndUrl) {
        log.debug("admin rest create cafe in");
        String[] nameAndUrlList = nameAndUrl.split("&");
        String cafeName = nameAndUrlList[0];
        String cafeImgUrl = nameAndUrlList[1];
        String categories = nameAndUrlList[2];

        if (cafeName.equals("")) {
            throw new NullPointerException();
        }
        return ResponseEntity.status(HttpStatus.CREATED).contentType(APPLICATION_JSON_UTF8).body(adminService.createCafe(cafeName, cafeImgUrl, categories));
    }

    @PostMapping(value = "/menus", produces = "application/json;charset=UTF-8")
    public ResponseEntity<MenuDto> createMenu(@RequestBody String menuTotalInfo) {
        log.debug("admin rest create menu in.");
        String[] menuInfoList = menuTotalInfo.split("&");
        String krName = menuInfoList[0];
        String enName = menuInfoList[1];
        int calories = Integer.parseInt(menuInfoList[2]);
        String category = menuInfoList[3];
        String description = menuInfoList[4];
        String cafeName = menuInfoList[5];
        String tagListString = menuInfoList[6];
        String pricePerSizeString = menuInfoList[7];
        String imageUrls = menuInfoList[8];

        return ResponseEntity.status(HttpStatus.CREATED).contentType(APPLICATION_JSON_UTF8).body(MenuDto.from(adminService.createMenu(krName, enName, calories, category, description, cafeName, tagListString, pricePerSizeString, imageUrls)));

    }
}
