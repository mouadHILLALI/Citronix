package com.citronix.citronix.dto;

import com.citronix.citronix.model.Farm;

import java.util.UUID;

public record FieldDto(UUID id , double surface , FarmDto farmDto) {
}
