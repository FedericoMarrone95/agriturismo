package com.example.agriturismo.controller;
import com.example.agriturismo.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/loginutente
@Controller
@RequestMapping("/loginutente")
public class LoginUtenteController
{
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public String getPage(
            HttpSession session,
            @RequestParam(name = "errore", required = false) String errore,
            Model model)
    {
        if(session.getAttribute("utente") != null)
            return "redirect:/riservatautente";
        model.addAttribute("errore", errore);
        return "loginutente";
    }

    @PostMapping
    public String formManager(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session
    )
    {
        if(!utenteService.controlloLogin(username,password,session))
            return "redirect:/loginutente?errore";
        return "redirect:/riservatautente";
    }
}