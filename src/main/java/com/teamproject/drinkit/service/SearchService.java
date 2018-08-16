package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.CafeRepository;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.MenuRepository;
import com.teamproject.drinkit.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
