package com.example.beadando.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "jegy")
@Data
public class Jegy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kapcsolat a diákhoz
    @ManyToOne
    @JoinColumn(name = "diakid", nullable = false)
    private Diak diak;

    @Column(nullable = false)
    private LocalDate datum;

    @Column(nullable = false)
    private Integer ertek;

    @Column(nullable = false)
    private String tipus;

    // Kapcsolat a tárgyhoz
    @ManyToOne
    @JoinColumn(name = "targyid", nullable = false)
    private Targy targy;
}
