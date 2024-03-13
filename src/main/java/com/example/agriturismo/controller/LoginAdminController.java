package com.example.agriturismo.controller;
import com.example.agriturismo.service.AdminService;
import com.example.agriturismo.service.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/loginadmin
@Controller
@RequestMapping("/loginadmin")
public class LoginAdminController
{
    @Autowired
    private AdminService adminService;

    @GetMapping
    public String getPage(
            HttpSession session,
            @RequestParam(name = "errore", required = false) String errore,
            Model model)
    {
        if(session.getAttribute("admin") != null)
            return "redirect:/adminprodotti";
        model.addAttribute("errore", errore);
        return "loginadmin";
    }

    @PostMapping
    public String formManager(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session
    )
    {
        if(!adminService.adminLogin(username,password,session))
            return "redirect:/loginadmin?errore";
        return "redirect:/adminprodotti";
    }
}