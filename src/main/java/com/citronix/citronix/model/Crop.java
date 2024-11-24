package com.citronix.citronix.model;

import com.citronix.citronix.enums.SeasonEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "crops")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @DecimalMin(value = "10" , message = "Quantity cannot be null")
    private double quantity;
    private LocalDate HarvestDate = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private SeasonEnum season;
    @ManyToMany
    private List<Tree> trees;
}
