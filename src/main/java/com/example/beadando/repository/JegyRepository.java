package com.example.beadando.repository;

import com.example.beadando.entity.Jegy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JegyRepository extends JpaRepository<Jegy, Long> {

    List<Jegy> findTop100ByOrderByDatumDesc();

    List<Jegy> findByDatumBetween(LocalDate from, LocalDate to);
}
