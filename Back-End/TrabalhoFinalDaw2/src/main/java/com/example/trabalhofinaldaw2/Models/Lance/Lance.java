package com.example.trabalhofinaldaw2.Models.Lance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Optional;

@Entity
public class Lance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Long idLeilao;
    private String statusLeilao;
    private String nomeLeilao;
    private String nomeAuthor;
    private String email;
    private String token;
    private Integer valorLeilao;
    private Integer valor;

    public Lance(long id, Long idLeilao, String statusLeilao, String nomeLeilao, String nomeAuthor, String email, String token, Integer valorLeilao, Integer valor) {
        this.id = id;
        this.idLeilao = idLeilao;
        this.statusLeilao = statusLeilao;
        this.nomeLeilao = nomeLeilao;
        this.nomeAuthor = nomeAuthor;
        this.email = email;
        this.token = token;
        this.valorLeilao = valorLeilao;
        this.valor = valor;
    }

    public Lance(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdLeilao() {
        return idLeilao;
    }

    public void setIdLeilao(Long idLeilao) {
        this.idLeilao = idLeilao;
    }

    public String getStatusLeilao() {
        return statusLeilao;
    }

    public void setStatusLeilao(String statusLeilao) {
        this.statusLeilao = statusLeilao;
    }

    public String getNomeLeilao() {
        return nomeLeilao;
    }

    public void setNomeLeilao(String nomeLeilao) {
        this.nomeLeilao = nomeLeilao;
    }

    public String getNomeAuthor() {
        return nomeAuthor;
    }

    public void setNomeAuthor(String nomeAuthor) {
        this.nomeAuthor = nomeAuthor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getValorLeilao() {
        return valorLeilao;
    }

    public void setValorLeilao(Integer valorLeilao) {
        this.valorLeilao = valorLeilao;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}