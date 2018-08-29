package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminRestController {
    private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);

    @Resource(name = "adminService")
    private AdminService adminService;

    @PostMapping("/cafes")
    public Cafe createCafe(@RequestBody String nameAndUrl) {
        log.debug("admin rest create in");
        String[] nameAndUrlList = nameAndUrl.split("&");
        String cafeName = nameAndUrlList[0];
        String cafeImgUrl = nameAndUrlList[1];
        String categories = nameAndUrlList[2];

        if (cafeName.equals("")) {
            throw new NullPointerException();
        }
        return adminService.createCafe(cafeName, cafeImgUrl, categories);
    }

}
