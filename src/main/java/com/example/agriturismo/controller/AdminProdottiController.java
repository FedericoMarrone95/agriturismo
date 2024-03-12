package com.example.agriturismo.controller;

import com.example.agriturismo.model.Prodotto;
import com.example.agriturismo.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminprodotti")
public class AdminProdottiController {
    @Autowired
    private ProdottoService prodottoService;
    private Prodotto prodotto;
    private Map<String, String> errori;
    @GetMapping
    public String getPage(
        Model model,
        @RequestParam(name = "id", required = false) Integer id
    ){
        List<Prodotto> prodotti = prodottoService.getProdotti();
        if(errori == null){
            prodotto = id == null ? new Prodotto() : prodottoService.getProdottoById(id);
        }
        model.addAttribute("prodotto", prodotti);
        return "admin-prodotti";

    }
}
