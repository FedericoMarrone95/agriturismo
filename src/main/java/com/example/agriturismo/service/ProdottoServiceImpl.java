package com.example.agriturismo.service;

import com.example.agriturismo.dao.ProdottoDao;
import com.example.agriturismo.model.Prodotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.regex.Pattern;


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
