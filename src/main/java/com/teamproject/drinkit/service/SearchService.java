package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES;

@Service
//@PreAuthorize("hasRole('ROLE_USER')")
@Transactional
public class SearchService {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    private List<Menu> findNewMenusByDate(LocalDateTime target) {
        return menuRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(target, LocalDateTime.now()).orElseGet(() -> new ArrayList<Menu>());
    }

    public FeaturedMenus findNewMenus() {
        FeaturedMenus newMenus = new FeaturedMenus();
        LocalDateTime oneMonthBeforeNow = LocalDateTime.now().minusMonths(1);       //현재로 부터 1달 전 까지의 메뉴를 구하기 위함.
        List<Menu> sortedMenus = findNewMenusByDate(oneMonthBeforeNow);

        for (int i = 0; i < 3; i++) {
            newMenus.addMenu(sortedMenus.get(i));
        }
        return newMenus;
    }

    public FeaturedMenus findTopReviewedMenus() {
        FeaturedMenus topMenus = new FeaturedMenus();
        List<Menu> target = menuRepository.findTopReviewed(new PageRequest(0, 3));

        for (int i = 0; i < target.size(); i++) {
            topMenus.addMenu(target.get(i));
        }
        return topMenus;
    }

    private boolean checkSpellIsPossible(Character.UnicodeBlock checkCode) {
        return (HANGUL_SYLLABLES == checkCode || BASIC_LATIN == checkCode);
    }

    @Transactional
    public Iterable<Menu> findWithKeyword(String inputString) throws NullPointerException, UnsupportedOperationException {
        log.debug("input is : " + inputString);
        Character.UnicodeBlock checkCode = Character.UnicodeBlock.of(inputString.charAt(0));

        if (checkSpellIsPossible(checkCode)) {
            return find(inputString);
        }else {
            throw new UnsupportedOperationException("영어와 한글만 지원되는 서비스 입니닷.");
        }
    }

    private Iterable<Menu> find(String keyword) {
        Tag targetTag = getTag(keyword);
        targetTag.plusSearchCount();        //tag 검색될 때, 1회 증가시킨다.(태그의 검색횟수를)
        return menuRepository.findByTagListContaining(targetTag).orElseThrow(() -> new NullPointerException("tag didn't exist."));
    }

    private Tag getTag(String tagName) {
        return tagRepository.findByTagName(tagName).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
    }

    public List<Tag> getSuggestedTags() {
        return tagRepository.findSuggestedTags();
    }

    public Tag findTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseGet(() -> new Tag());
        return tag;
    }
}
