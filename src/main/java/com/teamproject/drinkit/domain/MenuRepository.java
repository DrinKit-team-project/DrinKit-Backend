package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Iterable<Menu>> findByCafeIdAndCategory(Long id, String category);
    Optional<Menu> findByCafeIdAndId(Long cafeId, Long id);
    Optional<Iterable<Menu>> findByTagListContaining(Tag tag);
    Optional<List<Menu>> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime start, LocalDateTime end);

    @Query(
            value = "SELECT * from Menu m order by m.review_count desc limit 3",
            nativeQuery = true
    )
    List<Menu> findTopReviewed();

}
