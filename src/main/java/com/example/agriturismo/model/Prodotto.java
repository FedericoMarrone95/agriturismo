package com.example.agriturismo.model;

import jakarta.persistence.*;

@Entity
@Table(name="prodotti")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @Column
    private String descrizione;
    @Column
    private double prezzo;
    @Column
    private String tipologia;
    @Column
    private String immagine;
    @Column
    private int scorte;

    public int getId() {
        return id;
    }

    public Prodotto setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Prodotto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Prodotto setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public Prodotto setPrezzo(double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public String getTipologia() {
        return tipologia;
    }

    public Prodotto setTipologia(String tipologia) {
        this.tipologia = tipologia;
        return this;
    }

    public String getImmagine() {
        return immagine;
    }

    public Prodotto setImmagine(String immagine) {
        this.immagine = immagine;
        return this;
    }

    public int getScorte() {
        return scorte;
    }

    public Prodotto setScorte(int scorte) {
        this.scorte = scorte;
        return this;
    }
}
