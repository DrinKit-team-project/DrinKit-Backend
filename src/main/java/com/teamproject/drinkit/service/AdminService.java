package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.exception.NoSuchCategoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    public Cafe createCafe(String cafeName, String url, String categories) {
        log.debug("createCafe service in.");
        Cafe newCafe = makeNewCafe(cafeName, url);
        newCafe = addCategory(newCafe, categories);
        log.debug("cafe is : " + newCafe);
        return newCafe;
    }

    private Cafe makeNewCafe(String cafeName, String url) {
        Cafe newCafe = new Cafe(cafeName);
        newCafe.registerImageURL(url);
        return cafeRepository.save(newCafe);
    }

    private Cafe addCategory(Cafe targetCafe, String categories) {
        String[] splitedCategories = splitCategories(categories);

        for (String categoryName : splitedCategories) {
            targetCafe.addCategoryName(categoryName);
        }

        return targetCafe;
    }

    private String[] splitCategories(String categories) {
        return categories.split("/");
    }

    public Menu createMenu(String krName, String enName, int calories, String category, String description, String cafeName) {
        Menu newMenu = new Menu(krName, enName, calories, description, category);
        menuRepository.save(newMenu);
        Cafe targetCafe = cafeRepository.findByName(cafeName);
        checkCategory(targetCafe, category);
        return registerMenu(targetCafe, newMenu);
    }

    private void checkCategory(Cafe cafe, String categoryName) {
        if (!cafe.getCategoryNames().contains(categoryName)) {
            throw new NoSuchCategoryException("no such category exist.");
        }
    }

    private Menu registerMenu(Cafe cafe, Menu newMenu) {
        cafe.addMenu(newMenu);
        newMenu.registerCafe(cafe);
        return newMenu;
    }

}
