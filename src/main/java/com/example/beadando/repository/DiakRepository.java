package com.example.beadando.repository;

import com.example.beadando.entity.Diak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiakRepository extends JpaRepository<Diak, Long> {

    List<Diak> findByOsztalyOrderByNevAsc(String osztaly);
}
