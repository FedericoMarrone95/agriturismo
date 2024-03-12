package com.example.agriturismo.service;

import com.example.agriturismo.model.Tipologia;

import java.util.List;

public interface TipologiaService {
    void registraTipologia(Tipologia tipologia);
    List<Tipologia> getTipologie();
    Tipologia getTipologiaById(int id);
    void cancellaTipologia(int id);
}
