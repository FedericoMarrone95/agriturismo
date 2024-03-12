package com.example.agriturismo.dao;

import com.example.agriturismo.model.Prodotto;
import org.springframework.data.repository.CrudRepository;

public interface ProdottoDao extends CrudRepository <Prodotto, Integer> {

}
