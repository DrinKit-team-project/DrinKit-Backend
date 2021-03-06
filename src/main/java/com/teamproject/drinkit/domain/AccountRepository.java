package com.teamproject.drinkit.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByUserId(String userId);
    Optional<Account> findById(Long id);
    Optional<Account> findBySocialIdAndSocialProvider(String socialId, SocialProviders socialProviders);
    Optional<Account> findBySocialId(String socialId);
 }
