package com.example.agriturismo.dao;

import com.example.agriturismo.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdottoDao extends CrudRepository <Prodotto, Integer> {
    List<Prodotto> findByTipologiaId(int idTipologia);



}
