package com.citronix.citronix.dto;

import com.citronix.citronix.enums.SeasonEnum;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CropDto(UUID id , @DecimalMin(value = "2.5") double quantity , @NotNull(message = "Harvest date cannot be null") LocalDate harvestDate , @NotNull(message = "you must specify a season") SeasonEnum season , List<TreeDto> treeDtos) {
}
