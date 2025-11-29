package com.example.beadando.controller;

import com.example.beadando.repository.DiakRepository;
import com.example.beadando.repository.JegyRepository;
import com.example.beadando.repository.MessageRepository;
import com.example.beadando.repository.TargyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final DiakRepository diakRepository;
    private final TargyRepository targyRepository;
    private final JegyRepository jegyRepository;
    private final MessageRepository messageRepository;

    public MainController(DiakRepository diakRepository,
                          TargyRepository targyRepository,
                          JegyRepository jegyRepository,
                          MessageRepository messageRepository) {
        this.diakRepository = diakRepository;
        this.targyRepository = targyRepository;
        this.jegyRepository = jegyRepository;
        this.messageRepository = messageRepository;
    }

    // Főoldal
    @GetMapping("/")
    public String index(Model model) {
        long diakSzam = diakRepository.count();
        long targySzam = targyRepository.count();
        long jegySzam = jegyRepository.count();

        model.addAttribute("diakSzam", diakSzam);
        model.addAttribute("targySzam", targySzam);
        model.addAttribute("jegySzam", jegySzam);

        return "index";
    }

    // Adatbázis menü – diákok, tárgyak, jegyek
    @GetMapping("/adatbazis")
    public String adatbazis(Model model) {
        model.addAttribute("diakok", diakRepository.findAll());
        model.addAttribute("targyak", targyRepository.findAll());
        model.addAttribute("jegyek", jegyRepository.findAll());
        return "database";
    }

    // Diagram oldal (csak a template-et tölti be, az adatot /diagram/data adja)
    @GetMapping("/diagram")
    public String diagram() {
        return "diagram";
    }

    // Üzenetek listázása (admin / belépett felhasználó részére)
    @GetMapping("/uzenetek")
    public String uzenetek(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }
}
