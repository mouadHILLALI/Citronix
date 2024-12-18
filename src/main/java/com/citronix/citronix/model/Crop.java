package com.citronix.citronix.model;

import com.citronix.citronix.enums.SeasonEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "crops")
@Builder
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @DecimalMin(value = "10" , message = "Quantity cannot be null")
    private double quantity;
    @NotNull
    private LocalDate harvestDate;
    @Enumerated(EnumType.STRING)
    private SeasonEnum season;
    @ManyToMany
    @Valid
    private List<Tree> trees;
}
