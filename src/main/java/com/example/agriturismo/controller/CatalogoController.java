package com.example.agriturismo.controller;


import com.example.agriturismo.model.Admin;
import com.example.agriturismo.model.Tipologia;
import com.example.agriturismo.model.Utente;
import com.example.agriturismo.service.ProdottoService;
import com.example.agriturismo.service.TipologiaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private ProdottoService prodottoService;
    @Autowired
    TipologiaService tipologiaService;
    @GetMapping
    public String getPage(Model model, @RequestParam(required = false) Integer tipologiaId, HttpSession session) {
        if (tipologiaId != null) {
            model.addAttribute("prodotti", prodottoService.getProdottiByTipologia(tipologiaId));
            Tipologia tipologiaSelezionata = tipologiaService.getTipologiaById(tipologiaId);
            model.addAttribute("nomeTipologia", tipologiaSelezionata.getNome());
        } else {
            model.addAttribute("prodotti", prodottoService.getProdotti());
        }
        model.addAttribute("tipologie", tipologiaService.getTipologie());
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);
        Admin admin = (Admin) session.getAttribute("admin");
        model.addAttribute("admin", admin);
        return "catalogo";
    }






}
