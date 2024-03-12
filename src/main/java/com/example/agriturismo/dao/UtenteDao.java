package com.example.agriturismo.dao;

import com.example.agriturismo.model.Utente;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDao extends CrudRepository<Utente, Integer> {
        Utente findByUsernameAndPassword(String username, String password);
        Utente findByUsername(String username);
}
