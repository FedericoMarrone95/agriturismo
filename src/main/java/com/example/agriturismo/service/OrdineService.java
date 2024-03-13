package com.example.agriturismo.service;

import com.example.agriturismo.model.Ordine;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface OrdineService {
    void inviaOrdine(HttpSession session);
    List<Ordine> getOrdini();
}
