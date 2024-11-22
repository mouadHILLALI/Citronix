package com.citronix.citronix.repository;

import com.citronix.citronix.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
    Sale findSaleByCropId(UUID cropId);
}
