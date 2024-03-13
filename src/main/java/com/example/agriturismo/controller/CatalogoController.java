package com.example.agriturismo.controller;

import com.example.agriturismo.model.Prodotto;
import com.example.agriturismo.model.Tipologia;
import com.example.agriturismo.service.ProdottoService;
import com.example.agriturismo.service.TipologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private ProdottoService prodottoService;
    @GetMapping
    public String getPage(Model model){
        List<Prodotto> prodotti= prodottoService.getProdotti();
        model.addAttribute("prodotti", prodotti);
        return "catalogo";


    }

}
