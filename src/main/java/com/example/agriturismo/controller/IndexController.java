package com.example.agriturismo.controller;

import com.example.agriturismo.model.Utente;
import com.example.agriturismo.service.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String getPage(HttpSession session, Model model){
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);
        return "index";
    }
}
