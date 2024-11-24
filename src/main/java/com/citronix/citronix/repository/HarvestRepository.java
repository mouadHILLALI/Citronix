package com.citronix.citronix.repository;

import com.citronix.citronix.enums.SeasonEnum;
import com.citronix.citronix.model.Harvest;
import com.citronix.citronix.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID> {
    Harvest findByTreeAndSeason(Tree tree, SeasonEnum season);
}
