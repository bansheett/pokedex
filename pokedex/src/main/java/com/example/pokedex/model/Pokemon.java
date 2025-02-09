package com.example.pokedex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @Column(name = "immagine_url")
    private String immagineUrl;
    
    private String tipo;
    
    @Version
    private Long version;
    
    public Pokemon() {}

    public Pokemon(Long id, String nome, String immagineUrl, String tipo) {
        this.id = id;
        this.nome = nome;
        this.immagineUrl = immagineUrl;
        this.tipo = tipo;
    }

    // GETTER E SETTER
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getImmagineUrl() { return immagineUrl; }
    public void setImmagineUrl(String immagineUrl) { this.immagineUrl = immagineUrl; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}