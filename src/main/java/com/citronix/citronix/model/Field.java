package com.citronix.citronix.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Entity(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private double surface;
    @ManyToOne(fetch = FetchType.EAGER)
    private Farm farm;
    public Field(){
    }
    public Field(Farm farm, double surface, UUID id) {
        this.farm = farm;
        this.surface = surface;
        this.id = id;
    }

}
