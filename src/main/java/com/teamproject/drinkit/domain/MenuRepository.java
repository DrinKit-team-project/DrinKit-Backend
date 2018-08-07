package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Iterable<Menu> findByCafeIdAndCategory(Long id, String category);
    Menu findByCafeIdAndEnName(Long id, String enName);
}
