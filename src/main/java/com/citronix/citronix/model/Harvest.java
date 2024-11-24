package com.citronix.citronix.model;

import com.citronix.citronix.enums.SeasonEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "tree_id")
    private Tree tree;
    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;
    @NotNull
    private LocalDate harvestDate;
    @Enumerated(EnumType.STRING)
    @NotNull
    private SeasonEnum season;
    @DecimalMin(value = "2.5")
    private double quantity;
}
