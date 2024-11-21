package com.citronix.citronix.model;

import com.citronix.citronix.enums.TreeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "trees")
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "age cannot be null")
    @DecimalMin(value = "1" , message = "age cannot be less than 1")
    private double age;
    private LocalDate plantationDate;
    @DecimalMin(value = "2.5" , message = "Tree productivity cannot be less than 2.5 Kg per season")
    private double productivity;
    @Enumerated(EnumType.STRING)
    private TreeStatus status;
    @ManyToOne
    @JoinColumn(name = "field_id")
    private Field field;
}
