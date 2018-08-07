package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.Cafe;
import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeService {
    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    private Long findCafeId(String cafeName) {
        Cafe cafe = findCafe(cafeName);
        return cafe.getId();
    }

    public List<Cafe> findAllCafe() {
        return cafeRepository.findAll();
    }

    public Cafe findCafe(String cafeName) {
        return cafeRepository.findByName(cafeName);
    }

    public Iterable<Menu> findMenuList(String cafeName, String categoryName) {
        return menuRepository.findByCafeIdAndCategory(findCafeId(cafeName), categoryName);
    }

    public Menu findMenu(String cafeName, String menuName) {
        return menuRepository.findByCafeIdAndEnName(findCafeId(cafeName), menuName);
    }
}
