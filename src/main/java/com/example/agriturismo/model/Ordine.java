package com.example.agriturismo.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ordini")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_ora_ordine")
    private Date dataOraOrdine;
    @Column
    private Double importo;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_utente", referencedColumnName = "id")
    private Utente utente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataOraOrdine() {
        return dataOraOrdine;
    }

    public void setDataOraOrdine(Date dataOraOrdine) {
        this.dataOraOrdine = dataOraOrdine;
    }

    public Double getImporto() {
        return importo;
    }

    public void setImporto(Double importo) {
        this.importo = importo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
