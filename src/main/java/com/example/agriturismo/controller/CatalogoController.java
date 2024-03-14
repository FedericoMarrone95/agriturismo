package com.example.agriturismo.controller;


import com.example.agriturismo.service.ProdottoService;
import com.example.agriturismo.service.TipologiaService;
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
    public String getPage(Model model, @RequestParam(required = false) Integer tipologiaId) {
        if (tipologiaId != null) {
            model.addAttribute("prodotti", prodottoService.getProdottiByTipologia(tipologiaId));
        } else {
            model.addAttribute("prodotti", prodottoService.getProdotti());
        }
        model.addAttribute("tipologie", tipologiaService.getTipologie());
        return "catalogo";
    }






}
