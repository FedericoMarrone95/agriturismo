package com.example.agriturismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Pattern(regexp="[a-zA-Zàèìòù\\s']{1,50}", message = "caratteri non ammessi")
    private String nome;
    @Column
    @Pattern(regexp="[a-zA-Zàèòù\\s']{1,50}", message = "caratteri non ammessi")
    private String cognome;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String indirizzo;
    @Column
    private String email;
    @Column
    private String telefono;
    @OneToMany(mappedBy = "utente", cascade = CascadeType.REMOVE, fetch=FetchType.EAGER, orphanRemoval = true)
    private List<Ordine> ordini=new ArrayList<>();

    public int getId() {
        return id;
    }

    public Utente setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Utente setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCognome() {
        return cognome;
    }

    public Utente setCognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Utente setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Utente setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Utente setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Utente setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public Utente setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }
}
