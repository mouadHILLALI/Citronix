package com.citronix.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @FutureOrPresent
    private LocalDate saleDate;
    @NotNull
    @DecimalMin(value = "2")
    private double unitPrice;
    @NotNull
    @DecimalMin(value = "10")
    private double quantity;
    @NotNull
    @DecimalMin(value = "10")
    private double revenue;
    @NotNull
    private String client;
    @OneToOne
    private Crop crop;
}
