package com.teamproject.drinkit.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Iterable<Menu>> findByCafeIdAndCategory(Long id, String category);
    Optional<Menu> findByCafeIdAndId(Long cafeId, Long id);
    Optional<Iterable<Menu>> findByTagListContaining(Tag tag);
    Iterable<Menu> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime start, LocalDateTime end);

    Menu findByCafeIdAndEnName(Long id, String enName);
    Optional<Iterable<Menu>> findByEnName(String enName);
    Optional<Iterable<Menu>> findByKrName(String krName);
}
