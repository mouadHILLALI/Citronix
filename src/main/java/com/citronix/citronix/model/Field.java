package com.citronix.citronix.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Entity(name = "fields")
@Builder
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @DecimalMin(value = "1000.0" , inclusive = false , message = "field surface cannot be less than 1000 square meter")
    private double surface;
    @ManyToOne(fetch = FetchType.LAZY)
    private Farm farm;
    public Field(){
    }
    public Field(Farm farm, double surface, UUID id) {
        this.farm = farm;
        this.surface = surface;
        this.id = id;
    }

}
