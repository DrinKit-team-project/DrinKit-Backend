package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    Optional<Review> findByMenuIdAndId(Long menuId, Long id);
    List<Review> findAllByWriterId(Long writerId);
    Iterable<Review> findAllByMenuId(Long menuId);

}
