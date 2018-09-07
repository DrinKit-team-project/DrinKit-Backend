package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.*;
import com.teamproject.drinkit.dto.MenuDto;
import com.teamproject.drinkit.exception.NoSuchMenuException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@PreAuthorize("hasRole('ROLE_USER')")
@Transactional
public class CafeService {

    private static final Logger log = LoggerFactory.getLogger(CafeService.class);

    @Autowired
    private CafeRepository cafeRepository;

    public List<Cafe> findAllCafe() {
        return cafeRepository.findAll();
    }

    public Cafe findCafe(Long cafeId) {
        return cafeRepository.findById(cafeId).orElseThrow(() -> new NoSuchMenuException("no menu exist."));
    }

    public Cafe addCafe(Cafe cafe) {
        return cafeRepository.save(cafe);
    }
}
