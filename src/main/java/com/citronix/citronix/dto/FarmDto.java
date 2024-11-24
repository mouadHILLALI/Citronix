package com.citronix.citronix.dto;

import com.citronix.citronix.model.Field;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FarmDto(UUID id, String name , String location , double surfaceArea , LocalDate creationTime , LocalDate deletedAt , List<Field> fields) {
}
