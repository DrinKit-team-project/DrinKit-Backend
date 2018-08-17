package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.MenuDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CafeService {

    private static final Logger log = LoggerFactory.getLogger(CafeService.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private TagRepository tagRepository;

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

    public MenuDto makeMenuDto(Menu menu) {
        MenuDto menuDto = MenuDto.from(menu);
        return menuDto.copyReviews(menu).copyPricePerSize(menu).copyTagList(menu).copyImageURLs(menu);
    }

    @Transactional
    public void addReview(Review newReview, Menu menu) {
        newReview.registerReview(menu);
        menu.addReview(newReview);
        reviewRepository.save(newReview);
    }

    @Transactional
    public Tag getTag(String tagName) {
        Tag targetTag;
        try {
            targetTag = tagRepository.findByTagName(tagName);
        }catch (NullPointerException e) {
            targetTag = new Tag(tagName);
        }
        return targetTag;
    }

    @Transactional
    public void addTag(Tag tag, Menu menu) {
        menu.addTag(tag);
    }
}
