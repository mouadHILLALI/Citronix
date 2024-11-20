package com.citronix.citronix.repository;

import com.citronix.citronix.model.Farm;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface FarmRepository extends JpaRepository<Farm, Integer> , FarmRepositoryCustom {
    Farm findById(UUID id);
}
