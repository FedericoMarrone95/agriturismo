package com.example.agriturismo.service;

import com.example.agriturismo.dao.UtenteDao;
import com.example.agriturismo.model.Ordine;
import com.example.agriturismo.model.Utente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService{
    @Autowired
    private UtenteDao utenteDao;
    @Override
    public boolean controlloLogin(String username, String password, HttpSession session) {
        Utente utente= utenteDao.findByUsernameAndPassword(username, password);
        if(utente != null){
            session.setAttribute("utente", utente);
            return true;
        }
        return false;
    }

    @Override
    public void registraUtente(Utente utente) {
        utenteDao.save(utente);
    }

    @Override
    public boolean controlloUsername(String username) {
        if(utenteDao.findByUsername(username)==null)
            return true;
        return false;
    }

    @Override
    public List<Utente> getUtenti() {
        List<Utente> utenti = (List<Utente>) utenteDao.findAll();
        return utenti;
    }


}
