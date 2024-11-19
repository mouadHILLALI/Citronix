package com.citronix.citronix.repository;

import com.citronix.citronix.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {

}
