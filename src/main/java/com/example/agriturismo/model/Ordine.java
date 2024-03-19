package com.example.agriturismo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordini")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "data_ora_ordine")
    private LocalDateTime dataOraOrdine;
    @Column
    private Double importo;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_utente", referencedColumnName = "id")
    private Utente utente;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name= "ordini_prodotti_quantita",
            joinColumns = @JoinColumn(name="id_ordine", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_prodotto_quantita", referencedColumnName = "id")
    )
    private List<ProdottoQuantita> prodottiQuantita = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataOraOrdine() {
        return dataOraOrdine;
    }

    public void setDataOraOrdine(LocalDateTime dataOraOrdine) {
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

    public List<ProdottoQuantita> getProdottiQuantita() {
        return prodottiQuantita;
    }

    public void setProdottiQuantita(List<ProdottoQuantita> prodottiQuantita) {
        this.prodottiQuantita = prodottiQuantita;
    }
}
