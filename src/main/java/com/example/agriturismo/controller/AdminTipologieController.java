package com.example.agriturismo.controller;

import com.example.agriturismo.model.Tipologia;
import com.example.agriturismo.service.TipologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admintipologie")
public class AdminTipologieController {
    @Autowired
    private TipologiaService tipologiaService;

    @GetMapping
    public String getPage(Model model,
                          @RequestParam(name="id", required = false) Integer id)
    {
        List<Tipologia> tipologie = tipologiaService.getTipologie();
        Tipologia tipologia = id == null ? new Tipologia() : tipologiaService.getTipologiaById(id);
        model.addAttribute("tipologie", tipologie);
        model.addAttribute("tipologia", tipologia);
        return null; //inserire la pagina html
    }
}
