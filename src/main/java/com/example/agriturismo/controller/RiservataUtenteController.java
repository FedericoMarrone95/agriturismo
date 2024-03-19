package com.example.agriturismo.controller;

import com.example.agriturismo.model.Utente;
import com.example.agriturismo.service.OrdineService;
import com.example.agriturismo.service.ProdottoService;
import com.example.agriturismo.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/riservatautente")
public class RiservataUtenteController {
    @Autowired
    ProdottoService prodottoService;

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            HttpSession session,
            Model model,
            @RequestParam(name = "send", required = false) String send
    )
    {
        if(session.getAttribute("utente") == null)
            return "redirect:/loginutente";
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);
        model.addAttribute("carrello", prodottoService.getCarrello(session));
        model.addAttribute("totale", prodottoService.getTotaleCarrello(session));
        model.addAttribute("send", send);
        return "riservatautente";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session)
    {
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @GetMapping("/rimuovi")
    public String remove(
            @RequestParam("id") int id,
            HttpSession session
    )
    {
        prodottoService.rimuoviDalCarrello(id, session);
        return "redirect:/riservatautente";
    }

    @GetMapping("/invia")
    public String send(HttpSession session)
    {
        ordineService.inviaOrdine(session);
        return "redirect:/riservatautente?send";
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute("utente") Utente utente,
            BindingResult result,
            HttpSession session
    )
    {
        if(result.hasErrors())
            return "riservatautente";
        utenteService.registraUtente(utente);
        session.setAttribute("utente", utente);
        return "redirect:/riservatautente";
    }
    @GetMapping("/elimina")
    public String eliminaAccount(@RequestParam("id") int id,HttpSession session){
        utenteService.cancellaAccount(id);
        session.removeAttribute("utente");
        return "redirect:/";
    }

}