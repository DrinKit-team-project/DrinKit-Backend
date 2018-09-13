package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.exception.NoSuchCategoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;

@Service
@Transactional
//@PreAuthorize("hasRole('ROLE_USER')")
public class AdminService {
    private static final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TagRepository tagRepository;

    public Cafe createCafe(String cafeName, String url, String categories) {
        return cafeRepository.save(new Cafe(cafeName, url, categories));
    }

    public Menu createMenu(String krName, String enName, int calories, String category, String description, String cafeName, String tagListString, String pricePerSizeString, String imageUrls) {
        Menu newMenu = new Menu(krName, enName, calories, description, category);
        addTag(newMenu, tagListString);
        addImgUrls(newMenu, imageUrls);
        addPricePerSize(newMenu, pricePerSizeString);
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

    private Tag checkTagRepo(String newTagName) {
        return tagRepository.findByTagName(newTagName).orElseGet(() -> new Tag(newTagName));
    }

    private Menu addTag(Menu menu, String tagNameList) {
        String[] tags = tagNameList.split("/");
        for (String tagName : tags) {
            Tag newTag = checkTagRepo(tagName);
            tagRepository.save(newTag);
            menu.addTag(newTag);
        }
        return menu;
    }

    private MenuDto addPricePerSize(Menu menu, String pricePerSizeString) {
        String[] pricePerSizeUnits = pricePerSizeString.split("/");
        for (String pricePerSizeUnit: pricePerSizeUnits) {
            String[] sizeAndPrice;
            try {
                sizeAndPrice = pricePerSizeUnit.split(":");
            }catch (ArrayIndexOutOfBoundsException e) {
                throw new InputMismatchException();
            }
            PricePerSize newPricePerSize = new PricePerSize(sizeAndPrice[0], Integer.parseInt(sizeAndPrice[1]));
            menu.addPricePerSize(newPricePerSize);
        }
        return MenuDto.from(menu);
    }

    private Menu addImgUrls(Menu menu, String imageUrls) {
        String[] imageUrlList = imageUrls.split(",");
        for (String imageUrl : imageUrlList) {
            menu.addImageUrl(imageUrl);
        }
        return menu;
    }
}
