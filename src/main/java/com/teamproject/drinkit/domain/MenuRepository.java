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

//    @Query("select '*' from Menu order by (select count(reviews) FROM Menu) desc")
//    @Query("select all from Menu")
    @Query(
            value = "SELECT * from Menu m order by m.review_count desc",
            nativeQuery = true
    )
    List<Menu> findTopReviewed();

    Menu findByCafeIdAndEnName(Long id, String enName);
    Optional<Iterable<Menu>> findByEnName(String enName);
    Optional<Iterable<Menu>> findByKrName(String krName);
}
