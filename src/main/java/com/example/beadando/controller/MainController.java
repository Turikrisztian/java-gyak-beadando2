package com.example.beadando.controller;

import com.example.beadando.entity.Message;
import com.example.beadando.repository.DiakRepository;
import com.example.beadando.repository.JegyRepository;
import com.example.beadando.repository.MessageRepository;
import com.example.beadando.repository.TargyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    private DiakRepository diakRepository;

    @Autowired
    private TargyRepository targyRepository;

    @Autowired
    private JegyRepository jegyRepository;

    @Autowired
    private MessageRepository messageRepository;

    // Főoldal – statisztikák
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

    // Adatbázis menü – 3 tábla megjelenítése
    @GetMapping("/adatbazis")
    public String adatbazis(Model model) {
        model.addAttribute("diakok", diakRepository.findAll());
        model.addAttribute("targyak", targyRepository.findAll());
        model.addAttribute("jegyek", jegyRepository.findTop100ByOrderByDatumDesc());
        return "database";
    }

    // Kapcsolat űrlap megjelenítése
    @GetMapping("/kapcsolat")
    public String kapcsolatForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }

    // Kapcsolat űrlap feldolgozása
    @PostMapping("/kapcsolat")
    public String kapcsolatSubmit(@Valid @ModelAttribute("message") Message message,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "contact";
        }

        messageRepository.save(message);
        return "redirect:/kapcsolat?success";
    }

    // Üzenetek listázása – csak belépve (Spring Security kezeli a jogosultságot)
    @GetMapping("/uzenetek")
    public String uzenetek(Model model) {
        model.addAttribute("messages", messageRepository.findAllByOrderByCreatedAtDesc());
        return "messages";
    }
}