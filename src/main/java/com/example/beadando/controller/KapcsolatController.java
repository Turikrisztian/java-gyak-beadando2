package com.example.beadando.controller;

import com.example.beadando.entity.Message;
import com.example.beadando.repository.MessageRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class KapcsolatController {

    private final MessageRepository messageRepository;

    public KapcsolatController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/kapcsolat")
    public String kapcsolatForm(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }

    @PostMapping("/kapcsolat")
    public String kapcsolatSubmit(HttpServletRequest request, Model model) {

        String nev = request.getParameter("nev");
        String email = request.getParameter("email");
        String targy = request.getParameter("targy");
        String uzenetSzoveg = request.getParameter("uzenet");

        Message m = new Message();
        m.setNev(nev);
        m.setEmail(email);
        m.setTargy(targy);
        m.setUzenet(uzenetSzoveg);

        messageRepository.save(m);

        model.addAttribute("success", true);
        model.addAttribute("message", new Message());

        return "contact";
    }
}
