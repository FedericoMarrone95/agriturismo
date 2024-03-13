package com.example.agriturismo.service;

import jakarta.servlet.http.HttpSession;

public interface OrdineService {
    void inviaOrdine(HttpSession session);
}
