package com.citronix.citronix.dto;

import java.time.LocalDate;
import java.util.UUID;

public record FarmDto(UUID id, String name , String location , double surfaceArea , LocalDate creationTime , LocalDate deletedAt ) {
}
