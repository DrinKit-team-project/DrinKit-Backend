package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.exception.NoNewMenusException;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import com.teamproject.drinkit.exception.NoSuchTagsException;
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
@PreAuthorize("hasRole('ROLE_USER')")
@Transactional
public class SearchService {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TagRepository tagRepository;

    private List<Menu> findNewMenusByDate(LocalDateTime target) {
        return menuRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(target, LocalDateTime.now()).orElseGet(() -> new ArrayList<Menu>());
    }

    public List<Menu> findNewMenus() {
        LocalDateTime oneMonthBeforeNow = LocalDateTime.now().minusMonths(1);       //현재로 부터 1달 전 까지의 메뉴를 구하기 위함.
        return menuRepository.findTop3ByCreatedDateBetweenOrderByCreatedDateDesc(oneMonthBeforeNow, LocalDateTime.now()).orElseThrow(() -> new NoNewMenusException("최근 신메뉴가 없습니다."));
    }

    public List<Menu> findTopReviewedMenus() {
        return menuRepository.findTop3ByOrderByReviewCountDesc();
    }

    private boolean checkSpellIsPossible(Character.UnicodeBlock checkCode) {
        return (HANGUL_SYLLABLES == checkCode || BASIC_LATIN == checkCode);
    }

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
        return tagRepository.findTop4ByOrderBySearchCountDesc().orElseThrow(() -> new NoSuchTagsException("추천 태그가 존재하지 않습니다."));
    }

    public Tag findTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseGet(() -> new Tag());
        return tag;
    }
}
