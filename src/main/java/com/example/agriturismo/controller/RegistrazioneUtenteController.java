package com.example.agriturismo.controller;

import com.example.agriturismo.model.Admin;
import com.example.agriturismo.model.Utente;
import com.example.agriturismo.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registrazioneutente")
public class RegistrazioneUtenteController {
    @Autowired
    private UtenteService utenteService;
    @GetMapping
    public String getPage(Model model, HttpSession session,
                          @RequestParam(name="reg", required = false) String reg){
        Utente utente= new Utente();
        model.addAttribute("utente", utente);
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        model.addAttribute("reg", reg);
        return"registrazioneutente";
    }
    @PostMapping
    public String formManager(@Valid @ModelAttribute("utente") Utente utente, BindingResult result, Model model){
        if(result.hasErrors())
            return "registrazioneutente";
        if(!utenteService.controlloUsername(utente.getUsername())){
            model.addAttribute("duplicato","username non disponibile");
            return "registrazioneutente";
        }

        utenteService.registraUtente( utente);
        return "redirect:/registrazioneutente?reg";
    }

}