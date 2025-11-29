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

    @GetMapping("/diagram")
    public String showDiagramPage() {
        return "diagram";
    }

    @GetMapping("/diagram/data")
    @ResponseBody
    public List<Map<String, Object>> diagramData() {
        List<Jegy> jegyek = jegyRepository.findAll();

        Map<String, List<Integer>> subjectToGrades = new LinkedHashMap<>();
        for (Jegy j : jegyek) {
            String subjectName = j.getTargy().getNev();
            subjectToGrades
                    .computeIfAbsent(subjectName, k -> new ArrayList<>())
                    .add(j.getErtek());
        }

        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> entry : subjectToGrades.entrySet()) {
            String subject = entry.getKey();
            List<Integer> grades = entry.getValue();
            double avg = grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);

            Map<String, Object> point = new HashMap<>();
            point.put("label", subject);
            point.put("value", avg);
            result.add(point);
        }

        return result;
    }
}