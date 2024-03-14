package com.example.agriturismo.controller;

import com.example.agriturismo.model.Ordine;
import com.example.agriturismo.service.OrdineService;
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
    private OrdineService ordineService;

    private Ordine ordine;
    @GetMapping
    public String getPage(Model model,
                          @RequestParam(name="id", required = false)   Integer id){
        List<Ordine> ordini = ordineService.getOrdini();
        model.addAttribute("ordini", ordini);
        model.addAttribute("ordine", ordine);
        return "admin-utenti-ordinativi";
    }
}