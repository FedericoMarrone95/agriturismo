package com.example.agriturismo.service;

import com.example.agriturismo.model.Utente;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UtenteService {
    boolean controlloLogin(String username, String password, HttpSession session);
    void registraUtente(Utente utente);
    boolean controlloUsername(String username);
    List<Utente> getUtenti();
}
