package com.example.agriturismo.service;

import com.example.agriturismo.dao.ProdottoDao;
import com.example.agriturismo.model.Prodotto;

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
        // recuperiamo il prodotto che si vuole aggiungere
        Prodotto prodotto = getProdottoById(id);
        // capiamo se l'utente ha già un carrello oppure no
        List<Prodotto> carrello;
        if(session.getAttribute("carrello") != null) // lo ha già
        {
            // recuperiamo il carrello salvato in sessione
            carrello = (List<Prodotto>) session.getAttribute("carrello");
            // capiamo se il prodotto da acquistare è già nel carrello
            for(Prodotto p : carrello)
                if(p.getId() == id)
                    return false;
            // se il prodotto non è già presente, lo aggiungiamo
            carrello.add(prodotto);
            // sovrascriviamo il valore del carrello in sessione
            session.setAttribute("carrello", carrello);
            return true;
        }
        else // non ha nulla (primo acquisto)
        {
            // creiamo un nuovo carrello e ci aggiungiamo il prodotto
            carrello = new ArrayList<>();
            carrello.add(prodotto);
            // salviamo il carrello come attributo di sessione
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

    @SuppressWarnings("unchecked")
    @Override
    public void rimuoviDalCarrello(int id, HttpSession session)
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



}
