package com.example.agriturismo.service;

import com.example.agriturismo.dao.OrdineDao;
import com.example.agriturismo.model.Ordine;
import com.example.agriturismo.model.Prodotto;
import com.example.agriturismo.model.ProdottoQuantita;
import com.example.agriturismo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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
            ordine.setDataOraOrdine(LocalDateTime.now());
            ordine.setUtente(utente);

            List<ProdottoQuantita> carrelloQuantita = new ArrayList<>();
            for(Prodotto p : carrello){
                boolean prodottoPresente = false;
                for(ProdottoQuantita pq : carrelloQuantita){
                    if(p.getId() == pq.getProdotto().getId()){
                        prodottoPresente = true;
                        pq.setQuantita(pq.getQuantita() + 1);
                    }
                }
                if(!prodottoPresente){
                    ProdottoQuantita prodottoQuantita = new ProdottoQuantita(p, 1);
                    carrelloQuantita.add(prodottoQuantita);
                }
            }

            ordine.setProdottiQuantita(carrelloQuantita);
            ordine.setImporto(prodottoService.getTotaleCarrello(session));
            ordineDao.save(ordine);
            utente.getOrdini().add(ordine);
            session.setAttribute("utente", utente);
            session.removeAttribute("carrello");
        }
    }

    @Override
    public List<Ordine> getOrdini() {
        List<Ordine> ordini = (List<Ordine>) ordineDao.findAll();
        return ordini;
    }
    @Override
    public Ordine getOrdineById(int id) {
        Optional<Ordine> ordineOptional = ordineDao.findById(id);
        if(ordineOptional.isPresent())
            return ordineOptional.get();
        return null;
    }
}
