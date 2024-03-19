package com.example.agriturismo.service;

import com.example.agriturismo.dao.ProdottoDao;
import com.example.agriturismo.model.Prodotto;

import com.example.agriturismo.model.ProdottoQuantita;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class ProdottoServiceImpl implements ProdottoService {
    @Autowired
    private ProdottoDao prodottoDao;
    @Autowired
    private TipologiaService tipologiaService;
    @Override
    public List<Prodotto>  getProdotti() {
        List<Prodotto> prodotti= (List<Prodotto>) prodottoDao.findAll();
        return prodotti;
    }

    @Override
    public Prodotto getProdottoById(int id) {
        Optional<Prodotto> prodottoOptional=prodottoDao.findById(id);
        if(prodottoOptional.isPresent())
            return prodottoOptional.get();
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean aggiungiACarrello(int id, HttpSession session)
    {
        Prodotto prodotto = getProdottoById(id);
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new ArrayList<>();
            carrello.add(prodotto);
            session.setAttribute("carrello", carrello);
            return true;
        } else {
            carrello.add(prodotto);
            session.setAttribute("carrello", carrello);
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Prodotto> getCarrello(HttpSession session)
    {
        if(session.getAttribute("carrello") != null)
            return (List<Prodotto>) session.getAttribute("carrello");
        return null;
    }

    @Override
    public void diminuisciDalCarrello(int id, HttpSession session)
    {
        // otteniamo il carrello dalla sessione (sicuri di averlo)
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        // rimuoviamo il prodotto dal carrello (versione base)
        for(Prodotto p : carrello)
            if(p.getId() == id)
            {
                carrello.remove(p);
                break;
            }
        if(carrello.size() > 0) // sovrascriviamo
            session.setAttribute("carrello", carrello);
        else // rimuoviamo completamente
            session.removeAttribute("carrello");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void rimuoviDalCarrello(int id, HttpSession session)
    {
        // otteniamo il carrello dalla sessione (sicuri di averlo)
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        // rimuoviamo il prodotto dal carrello (versione avanzata)
        carrello = carrello
                .stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        // capiamo se il carrello ha ancora qualcosa oppure no
        if(carrello.size() > 0) // sovrascriviamo
            session.setAttribute("carrello", carrello);
        else // rimuoviamo completamente
            session.removeAttribute("carrello");
    }

    @SuppressWarnings("unchecked")
    @Override
    public double getTotaleCarrello(HttpSession session)
    {
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        if(carrello != null)
            return carrello
                    .stream()
                    .mapToDouble(Prodotto::getPrezzo)
                    .reduce(0.0, (p1,p2) -> p1 + p2);
        return 0;
    }


    @Override
    public Object validaProdotto(Prodotto prodotto, String nome, String descrizione, String prezzo, String scorte, int idTipologia){
        prodotto.setTipologia(tipologiaService.getTipologiaById(idTipologia));
        prodotto.setNome(nome);
        Map<String, String> errori = new HashMap<>();
        if(!Pattern.matches("[a-zA-Z0-9\\sàèìòù,.-]{1,50}", nome)){
            errori.put("nome", "Caratteri non ammessi");
        }
        try{
            prodotto.setPrezzo(Double.parseDouble(prezzo));
        }catch (Exception e){
            errori.put("prezzo", "il prezzo indicato non è corretto");
        }
        if(errori.size()>0){
            return new Object[]{prodotto, errori};
        } else{
            return null;
        }
    }
    @Override
    public void registraProdotto(Prodotto prodotto, String nome, String descrizione,
                                 String prezzo, String scorte, int idTipologia, MultipartFile immagine){
        prodotto.setNome(nome);
        prodotto.setDescrizione(descrizione);
        prodotto.setPrezzo(Double.parseDouble(prezzo));
        prodotto.setScorte(Integer.parseInt(scorte));
        prodotto.setTipologia(tipologiaService.getTipologiaById(idTipologia));
        if(immagine != null && !immagine.isEmpty()){
            try{
                String formato = immagine.getContentType();
                String immagineCodificata = "data:" + formato + ";base64," +
                        Base64.getEncoder().encodeToString(immagine.getBytes());
                prodotto.setImmagine(immagineCodificata);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        prodottoDao.save(prodotto);
    }

    @Override
    public void cancellaProdotto(int id) {
        prodottoDao.deleteById(id);
    }

    @Override
    public List<Prodotto> getProdottiByTipologia(int idTipologia) {
        return prodottoDao.findByTipologiaId(idTipologia);
    }

    @Override
    public List<ProdottoQuantita> trasformaACarrelloQuantita(HttpSession session){
        List<Prodotto> carrello = (List<Prodotto>) session.getAttribute("carrello");
        if(carrello == null)
            return null;
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
        return carrelloQuantita;
    }


}
