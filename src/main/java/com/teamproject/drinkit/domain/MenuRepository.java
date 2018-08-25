package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Iterable<Menu> findByCafeIdAndCategory(Long id, String category);
    Menu findByCafeIdAndEnName(Long id, String enName);
    Iterable<Menu> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime start, LocalDateTime end);
}
