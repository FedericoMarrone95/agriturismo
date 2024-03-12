package com.example.agriturismo.service;

import com.example.agriturismo.model.Prodotto;

import java.util.List;

public interface ProdottoService {
    List<Prodotto> getProdotti();

    Prodotto getProdottoById(int id);
}
