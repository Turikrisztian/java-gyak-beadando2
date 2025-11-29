package com.example.beadando.controller;

import com.example.beadando.entity.Diak;
import com.example.beadando.repository.DiakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/crud/diak")
public class CrudController {

    @Autowired
    private DiakRepository diakRepository;

    // Lista
    @GetMapping
    public String list(Model model) {
        model.addAttribute("diakok", diakRepository.findAll());
        return "crud_list";
    }

    // Új diák űrlap
    @GetMapping("/uj")
    public String newForm(Model model) {
        model.addAttribute("diak", new Diak());
        model.addAttribute("mode", "create");
        return "crud_form";
    }

    // Új diák mentése
    @PostMapping("/uj")
    public String create(@ModelAttribute("diak") Diak diak) {
        diakRepository.save(diak);
        return "redirect:/crud/diak";
    }

    // Szerkesztő űrlap
    @GetMapping("/szerkesztes/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Diak diak = diakRepository.findById(id).orElseThrow();
        model.addAttribute("diak", diak);
        model.addAttribute("mode", "edit");
        return "crud_form";
    }

    // Módosítás mentése
    @PostMapping("/szerkesztes/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("diak") Diak diak) {
        diak.setId(id);
        diakRepository.save(diak);
        return "redirect:/crud/diak";
    }

    // Törlés
    @GetMapping("/torles/{id}")
    public String delete(@PathVariable Long id) {
        diakRepository.deleteById(id);
        return "redirect:/crud/diak";
    }
}