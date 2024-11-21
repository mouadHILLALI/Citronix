package com.citronix.citronix.repository;

import com.citronix.citronix.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CropRepository extends JpaRepository<Crop, UUID> {
}
