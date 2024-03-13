package com.example.agriturismo.service;

import com.example.agriturismo.dao.OrdineDao;
import com.example.agriturismo.model.Ordine;
import com.example.agriturismo.model.Prodotto;
import com.example.agriturismo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class OrdineServiceImpl implements OrdineService{
    @Autowired
    private OrdineDao ordineDao;
    @Autowired
    private  ProdottoService prodottoService;

    @SuppressWarnings("unchecked")
    @Override
    public void inviaOrdine(HttpSession session) {
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        Utente utente = (Utente) session.getAttribute("utente");
        if(carrello != null & utente != null){
            Ordine ordine = new Ordine();
            //ordine.setDataOraOrdine(LocalDate.now());
            ordine.setUtente(utente);
            ordine.setProdotti(carrello);
            ordine.setImporto(prodottoService.getTotaleCarrello(session));
            ordineDao.save(ordine);
            //utente.getOrdini().add(ordine);
            session.setAttribute("utente", utente);
            session.removeAttribute("carrello");
        }
    }
}
