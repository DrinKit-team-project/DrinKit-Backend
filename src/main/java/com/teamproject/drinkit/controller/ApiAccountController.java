package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.Account;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.security.AccountDetails;
import com.teamproject.drinkit.security.AccountDetailsService;
import com.teamproject.drinkit.service.MenuService;
import com.teamproject.drinkit.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@PreAuthorize("hasRole('ROLE_USER')")
public class ApiAccountController {
    private static final Logger log = LoggerFactory.getLogger(ApiAccountController.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @GetMapping("/{id}/reviews")
    public List<Review> showReviews(@PathVariable Long id){
        return reviewService.findAllByWriter(id);
    }

    @PutMapping("")
    public ResponseEntity<Account> editNickName(@RequestHeader("Authorization") String header, @RequestParam(name = "nickname") String newNickname){
        return new ResponseEntity<>(accountDetailsService.editNickname(header, newNickname), HttpStatus.OK);
    }

    @PostMapping("/{id}/likes/menus/{menuId}")
    public ResponseEntity<Account> addFavoriteMenu(@PathVariable Long id, @PathVariable Long menuId){
        return new ResponseEntity<>(accountDetailsService.addFavoriteMenu(id, menuId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/removes/menus/{menuId}")
    public ResponseEntity<Account> removeFavoriteMenu(@PathVariable Long id, @PathVariable Long menuId){
        return new ResponseEntity<>(accountDetailsService.removeFavoriteMenu(id, menuId), HttpStatus.OK);
    }

    @GetMapping("/{id}/likes/menus")
    public Iterable<Menu> showFavoriteMenus(@PathVariable Long id){
        return accountDetailsService.findFavoriteMenus(id);
    }
}
