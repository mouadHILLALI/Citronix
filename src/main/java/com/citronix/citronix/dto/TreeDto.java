package com.citronix.citronix.dto;

import com.citronix.citronix.enums.TreeStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TreeDto(UUID id, double age , LocalDate plantationDate , double productivity , TreeStatus status , FieldDto fieldDto) {
}
