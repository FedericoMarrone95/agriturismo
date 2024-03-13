package com.example.agriturismo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chisiamo")
public class ChiSiamoController {
    @GetMapping
    public String getPage(){
        return "chi-siamo";
    }


}
