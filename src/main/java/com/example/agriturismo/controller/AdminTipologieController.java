package com.example.agriturismo.controller;

import com.example.agriturismo.model.Tipologia;
import com.example.agriturismo.service.TipologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "admin-tipologie"; //inserire la pagina html
    }
    @PostMapping
    public String formManager(@ModelAttribute("tipologia") Tipologia tipologia){
        tipologiaService.registraTipologia(tipologia);
        return "redirect:/admintipologie";
    }

    @GetMapping("/elimina")
    public String eliminaTipologia(@RequestParam("id") int id){
        tipologiaService.cancellaTipologia(id);
        return "redirect:/admintipologie";
    }
}
