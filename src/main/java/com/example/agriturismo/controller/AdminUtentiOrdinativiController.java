package com.example.agriturismo.controller;

import com.example.agriturismo.model.Ordine;
import com.example.agriturismo.model.Utente;
import com.example.agriturismo.service.OrdineService;
import com.example.agriturismo.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminutentiordinativi")
public class AdminUtentiOrdinativiController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private OrdineService ordineService;
    private Ordine ordine;
    @GetMapping
    public String getPage(Model model, HttpSession session,
                          @RequestParam(name="id", required = false)   Integer id){
        if(session.getAttribute("admin") == null)
            return "redirect:/loginadmin";
        List<Ordine> ordini = ordineService.getOrdini();
        List<Utente> utenti = utenteService.getUtenti();
        model.addAttribute("ordini", ordini);
        model.addAttribute("utenti", utenti);
        model.addAttribute("ordine", ordine);
        return "admin-utenti-ordinativi";
    }
}
