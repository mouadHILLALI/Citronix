package com.citronix.citronix.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record SaleDto(UUID id , LocalDate saleDate , @NotNull double unitPrice , @NotNull double quantity ,  double revenue , @NotNull String client ,  @NotNull CropDto cropDto) {
}
