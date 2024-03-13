package com.example.agriturismo.dao;

import com.example.agriturismo.model.Ordine;
import org.springframework.data.repository.CrudRepository;

public interface OrdineDao extends CrudRepository<Ordine, Integer> {
}
