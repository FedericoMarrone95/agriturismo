package com.example.agriturismo.service;

import com.example.agriturismo.model.Prodotto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProdottoService {
    List<Prodotto> getProdotti();
    Prodotto getProdottoById(int id);
    boolean aggiungiACarrello(int id, HttpSession session);
    List<Prodotto> getCarrello(HttpSession session);
    void rimuoviDalCarrello(int id, HttpSession session);
    double getTotaleCarrello(HttpSession session);
    Object validaProdotto(Prodotto prodotto, String nome, String descrizione, String prezzo, String scorte, int idTipologia);
    void registraProdotto(Prodotto prodotto, String nome, String descrizione, String prezzo, String scorte, int idTipologia, MultipartFile immagine);
    void cancellaProdotto(int id);

}
