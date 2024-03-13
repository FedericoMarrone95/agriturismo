package com.example.agriturismo.controller;

import com.example.agriturismo.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/riservatautente")
public class RiservataUtenteController {
    @Autowired
    ProdottoService prodottoService;

}
