package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagName(String tagName);

//    @Query("SELECT '*' FROM Menu JOIN Tag WHERE Tag = LOWER(:tag)")
//    Optional<Iterable<Menu>> findBySearchTagName(@Param("tag") String tagName);
}
