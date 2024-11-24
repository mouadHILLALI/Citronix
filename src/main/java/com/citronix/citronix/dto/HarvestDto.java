package com.citronix.citronix.dto;

import com.citronix.citronix.enums.SeasonEnum;

import java.time.LocalDate;
import java.util.UUID;

public record HarvestDto(UUID id , TreeDto treeDto , CropDto cropDto , SeasonEnum season , double quantity , LocalDate harvestDate) {
}
