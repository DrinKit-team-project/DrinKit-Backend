package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(Long id);
    Optional<Review> findByMenuIdAndId(Long menuId, Long id);
}
