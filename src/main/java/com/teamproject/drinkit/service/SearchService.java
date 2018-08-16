package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES;

@Service
public class SearchService {
    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Iterable<Menu> findNewMenu() {
        LocalDateTime oneMonthBeforeNow = LocalDateTime.now().minusMonths(1);
        return menuRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(oneMonthBeforeNow, LocalDateTime.now());
    }

//    public Iterable<Menu> findTopReviewdMenu() {
//        이 부분 구현 필요. (top rated 메뉴)
//    }

    public void checkCharacter(String inputString) throws UnsupportedOperationException {
        if (HANGUL_SYLLABLES == Character.UnicodeBlock.of(inputString.charAt(0))) {
            System.out.println("한글");
        }else if (BASIC_LATIN == Character.UnicodeBlock.of(inputString.charAt(0))){
            System.out.println("english");
        }else {
            throw new UnsupportedOperationException("영어와 한글만 지원되는 서비스 입니닷.");
        }
    }

    public void searchWithHangul(String inputString) {

    }
}
