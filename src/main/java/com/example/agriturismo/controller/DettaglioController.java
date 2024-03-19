package com.example.agriturismo.controller;
import com.example.agriturismo.model.Admin;
import com.example.agriturismo.model.Prodotto;
import com.example.agriturismo.model.Utente;
import com.example.agriturismo.service.ProdottoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/dettaglio?id=...
@Controller
@RequestMapping("/dettaglio")
public class DettaglioController
{
  @Autowired
  private ProdottoService prodottoService;

  @GetMapping
  public String getPage(HttpSession session,
          @RequestParam("id") int id,
          Model model,
          @RequestParam(name = "add", required = false) String result
  )
  {
    Prodotto prodotto = prodottoService.getProdottoById(id);
    model.addAttribute("prodotto", prodotto);
    model.addAttribute("result", result);
    Admin admin = (Admin) session.getAttribute("admin");
    model.addAttribute("admin", admin);
    Utente utente = (Utente) session.getAttribute("utente");
    model.addAttribute("utente", utente);
    return "dettaglio";
  }

  @GetMapping("/aggiungi")
  public String add(
          @RequestParam("id") int id,
          HttpSession session
  )
  {
    if(!prodottoService.aggiungiACarrello(id, session))
      return "redirect:/dettaglio?id=" + id + "&add=n";
    return "redirect:/dettaglio?id=" + id + "&add=y";
  }
}