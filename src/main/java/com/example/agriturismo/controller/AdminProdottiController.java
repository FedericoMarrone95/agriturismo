package com.example.agriturismo.controller;

import com.example.agriturismo.model.Prodotto;
import com.example.agriturismo.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @SuppressWarnings("unchecked")
    @PostMapping
    public String formManager(
            @RequestParam("nome") String nome,
            @RequestParam("descrizione") String descrizione,
            @RequestParam("prezzo") String prezzo,
            @RequestParam("scorte") String scorte,
            @RequestParam("tipologia") int idTipologia,
            @RequestParam(name="immagine", required = false) MultipartFile immagine
    ){
        Object risultatoValidazione = prodottoService.validaProdotto(prodotto, nome, descrizione, prezzo, scorte, idTipologia);
        // se abbiamo errori di validazione
        if(risultatoValidazione != null)
        {
            prodotto = (Prodotto) ((Object[])risultatoValidazione)[0];
            errori = (Map<String, String>) ((Object[])risultatoValidazione)[1];
            return "redirect:/adminlibri";
        }
        prodottoService.registraProdotto(prodotto, nome, descrizione, prezzo, scorte, idTipologia, immagine);
        prodotto = null;
        errori = null;
        return "redirect:/adminlibri";
    }
    @GetMapping("/elimina")
    public String eliminaProdotto(@RequestParam("id") int id){
        prodottoService.cancellaProdotto(id);
        return "redirect:/adminprodotti";
    }
}
