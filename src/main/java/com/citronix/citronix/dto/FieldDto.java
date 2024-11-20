package com.citronix.citronix.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FieldDto(UUID id , @Nonnull @DecimalMin(value = "1000.0" , inclusive = false , message = "field surface cannot be less than 1000 square meter") double surface , @NotNull(message = "farm Id cannot be Null") FarmDto farmDto) {
}
