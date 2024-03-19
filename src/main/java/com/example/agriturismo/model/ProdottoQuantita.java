package com.example.agriturismo.model;

import jakarta.persistence.*;

@Entity
@Table(name="prodotti_quantita")
public class ProdottoQuantita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_prodotto", referencedColumnName = "id")
    private Prodotto prodotto;
    @Column
    private int quantita;

    public ProdottoQuantita() {
    }

    public ProdottoQuantita(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}