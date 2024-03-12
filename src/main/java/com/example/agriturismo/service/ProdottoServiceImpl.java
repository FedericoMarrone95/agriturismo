package com.example.agriturismo.service;

import com.example.agriturismo.dao.ProdottoDao;
import com.example.agriturismo.model.Prodotto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProdottoServiceImpl implements ProdottoService {
    @Autowired
    private ProdottoDao prodottoDao;
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
}
