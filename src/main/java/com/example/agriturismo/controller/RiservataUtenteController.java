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
    private ProdottoService prodottoService;

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            HttpSession session,
            Model model,
            @RequestParam(name = "send", required = false) String send,
            @RequestParam(name = "add", required = false) String result
    )
    {
        if(session.getAttribute("utente") == null)
            return "redirect:/loginutente";
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);
        model.addAttribute("carrello", prodottoService.getCarrello(session));
        model.addAttribute("carrelloQuantita", prodottoService.trasformaACarrelloQuantita(session));
        model.addAttribute("totale", prodottoService.getTotaleCarrello(session));
        model.addAttribute("send", send);
        model.addAttribute("result", result);
        return "riservatautente";
    }

    @GetMapping("/logout")
    public String userLogout(HttpSession session)
    {
        session.removeAttribute("utente");
        return "redirect:/";
    }

    @GetMapping("/aggiungi")
    public String increase(
            @RequestParam("id") int id,
            HttpSession session
    )
    {
        if(!prodottoService.aggiungiACarrello(id, session))
            return "redirect:/riservatautente?id=" + id + "&add=n";
        return "redirect:/riservatautente?id=" + id + "&add=y";
    }

    @GetMapping("/diminuisci")
    public String decrease(
            @RequestParam("id") int id,
            HttpSession session
    )
    {
        prodottoService.diminuisciDalCarrello(id, session);
        return "redirect:/riservatautente";
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
        prodottoService.modificaScorte(session);
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
}