package com.example.beadando.controller;

import com.example.beadando.entity.Jegy;
import com.example.beadando.repository.JegyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class DiagramController {

    @Autowired
    private JegyRepository jegyRepository;

    @GetMapping("/diagram/data")
    @ResponseBody
    public List<Map<String, Object>> getDiagramData() {
        List<Jegy> jegyek = jegyRepository.findAll();

        Map<String, List<Integer>> targyErtek = new LinkedHashMap<>();

        for (Jegy j : jegyek) {
            if (j.getTargy() != null && j.getTargy().getNev() != null) {
                String nev = j.getTargy().getNev();
                targyErtek.putIfAbsent(nev, new ArrayList<>());
                targyErtek.get(nev).add(j.getErtek());
            }
        }

        List<Map<String, Object>> response = new ArrayList<>();

        for (var entry : targyErtek.entrySet()) {
            double avg = entry.getValue()
                    .stream()
                    .mapToInt(i -> i)
                    .average()
                    .orElse(0);

            Map<String, Object> row = new HashMap<>();
            row.put("label", entry.getKey());
            row.put("value", avg);

            response.add(row);
        }

        return response;
    }
}
