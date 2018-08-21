package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Iterable<Menu>> findByCafeIdAndCategory(Long id, String category);
    Optional<Menu> findByCafeIdAndId(Long cafeId, Long id);
    Menu findByCafeIdAndEnName(Long id, String enName);
    Iterable<Menu> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime start, LocalDateTime end);
    Optional<Iterable<Menu>> findByEnName(String enName);
    Optional<Iterable<Menu>> findByKrName(String krName);
//    Iterable<Menu> findByTag(String tagword);
//    Iterable<Menu> findByEnNameOrKrName(String keyword);
}
