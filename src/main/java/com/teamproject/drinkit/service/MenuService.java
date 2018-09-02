package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@PreAuthorize("hasRole('ROLE_USER')")
@Transactional
public class MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findAllMenu() {
        return menuRepository.findAll();
    }

    public Iterable<Menu> findMenuList(Long cafeId, String categoryName) {
        return menuRepository.findByCafeIdAndCategory(cafeId, categoryName).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
    }

    public Menu findMenu(Long cafeId, Long menuId) {
        log.debug("find menu service in.");
        log.debug("cafeId is " + cafeId + ", menuId is " + menuId);
        return menuRepository.findByCafeIdAndId(cafeId, menuId).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
    }

    public MenuDto makeMenuDto(Menu menu) {
        MenuDto menuDto = MenuDto.from(menu);
        return menuDto.copyReviews(menu).copyPricePerSize(menu).copyTagList(menu).copyImageURLs(menu);
    }

    public Menu addMenu(Long cafeId, MenuDto menuDto) {
        Cafe targetCafe = cafeRepository.findById(cafeId).orElseThrow(() -> new NullPointerException("cafe didn't exist."));
        Menu newMenu = Menu.from(menuDto);
        targetCafe.addMenu(newMenu);
        newMenu.registerCafe(targetCafe);

        cafeRepository.save(targetCafe);
        return menuRepository.save(newMenu);
    }

    public void addTag(Tag tag, Menu menu) {
        menu.addTag(tag);
    }
}
