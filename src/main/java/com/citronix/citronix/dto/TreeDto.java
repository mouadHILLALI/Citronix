package com.citronix.citronix.dto;

import com.citronix.citronix.enums.TreeStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record TreeDto(UUID id, @DecimalMin(value = "1" , message = "age cannot be null") double age , @NotNull(message = "plantation date cannot be null") LocalDate plantationDate ,  double productivity , TreeStatus status , FieldDto fieldDto) {
}
