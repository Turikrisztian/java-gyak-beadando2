package com.example.beadando.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nev;
    private String email;
    private String targy;

    @Column(length = 2000)
    private String uzenet;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ====== GETTEREK & SETTEREK ======

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNev() { return nev; }
    public void setNev(String nev) { this.nev = nev; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTargy() { return targy; }
    public void setTargy(String targy) { this.targy = targy; }

    public String getUzenet() { return uzenet; }
    public void setUzenet(String uzenet) { this.uzenet = uzenet; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
