package com.example.beadando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "targy")
@Data
public class Targy {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nev;

    @Column(nullable = false)
    private String kategoria;
}
