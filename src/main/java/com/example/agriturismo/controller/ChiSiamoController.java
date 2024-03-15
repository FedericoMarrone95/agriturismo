package com.example.agriturismo.controller;

import com.example.agriturismo.model.Admin;
import com.example.agriturismo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chisiamo")
public class ChiSiamoController {
    @GetMapping
    public String getPage(HttpSession session, Model model){
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "chi-siamo";
    }


}
