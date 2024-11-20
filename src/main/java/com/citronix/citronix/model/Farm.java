package com.citronix.citronix.model;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity(name = "farms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Field> fields;
}
