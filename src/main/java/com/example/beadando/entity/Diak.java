package com.example.beadando.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "diak")
@Data
public class Diak {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nev;

    @Column(nullable = false)
    private String osztaly;

    // -1 = fiú, 0 = lány a kapott fájlok alapján
    @Column(nullable = false)
    private Integer fiu;
}
