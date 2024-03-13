package com.example.agriturismo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(
            name= "ordini_prodotti",
            joinColumns = @JoinColumn(name="id_ordine", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_prodotto", referencedColumnName = "id")
    )
    private List<Prodotto> prodotti = new ArrayList<>();

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

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
}
