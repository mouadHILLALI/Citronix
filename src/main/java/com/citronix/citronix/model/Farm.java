package com.citronix.citronix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Timestamp;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.UUID;


@Entity(name = "farms")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    private double surfaceArea;
    @Timestamp
    private LocalDate creationTime;
    private LocalDate deletedAt;

    public Farm(){

    }
    public Farm(UUID id, String name, String location, double surfaceArea, LocalDate creationTime, LocalDate deletedAt) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.surfaceArea = surfaceArea;
        this.creationTime = creationTime;
        this.deletedAt = deletedAt;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }


}
