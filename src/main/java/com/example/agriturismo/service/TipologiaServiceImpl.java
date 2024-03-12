package com.example.agriturismo.service;

import com.example.agriturismo.dao.TipologiaDao;
import com.example.agriturismo.model.Tipologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipologiaServiceImpl implements TipologiaService{
    @Autowired
    private TipologiaDao tipologiaDao;

    @Override
    public void registraTipologia(Tipologia tipologia) {
        tipologiaDao.save(tipologia);
    }

    @Override
    public List<Tipologia> getTipologie() {
        return (List<Tipologia>) tipologiaDao.findAll();
    }

    @Override
    public Tipologia getTipologiaById(int id) {
        return tipologiaDao.findById(id).get();
    }

    @Override
    public void cancellaTipologia(int id) {
        tipologiaDao.deleteById(id);

    }
}
