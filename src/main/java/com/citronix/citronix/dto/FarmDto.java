package com.citronix.citronix.dto;

import com.citronix.citronix.model.Field;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FarmDto(UUID id, @NotNull(message = "name cannot be null") String name , @NotNull(message = "location cannot be null") String location , @DecimalMin(value = "1000" , message = "minimal surface area of a farm is 1000 square meters") @NotNull(message = "surface area cannot be null") double surfaceArea , LocalDate creationTime , LocalDate deletedAt , List<Field> fields) {
}
