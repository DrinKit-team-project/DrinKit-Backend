package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES;

@Service
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

    public Iterable<Menu> findNewMenu() {
        LocalDateTime oneMonthBeforeNow = LocalDateTime.now().minusMonths(1);
        return menuRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(oneMonthBeforeNow, LocalDateTime.now());
    }

//    public Iterable<Menu> findTopReviewdMenu() {
//        이 부분 구현 필요. (top rated 메뉴)
//    }

    public Iterable<Menu> checkCharacter(String inputString) throws NullPointerException, UnsupportedOperationException {
        log.debug("input is : " + inputString);
        Character.UnicodeBlock checkCode;
        try {
            checkCode = Character.UnicodeBlock.of(inputString.charAt(0));
        }catch (NullPointerException e) {
            log.debug("error : null pointer exception occur.");
            throw new NullPointerException();
        }catch (StringIndexOutOfBoundsException e) {
            log.debug("error : null pointer exception occur.");
            throw new NullPointerException();
        }

        if (HANGUL_SYLLABLES == checkCode || BASIC_LATIN == checkCode) {
            return find(inputString);
        }else {
            throw new UnsupportedOperationException("영어와 한글만 지원되는 서비스 입니닷.");
        }
    }

    private Iterable<Menu> find(String keyword) {
        Tag targetTag = getTag(keyword);
        return menuRepository.findByTagListContaining(targetTag).orElseThrow(() -> new NullPointerException("tag didn't exist."));
    }

    private Tag getTag(String tagName) {
        return tagRepository.findByTagName(tagName).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
    }

}
