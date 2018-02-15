package br.edu.ufcg.labsi.entidades;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    private String login;
    private String nome;
    private String senha;
    @Autowired
    private List<Artista> artistasFavoritos;

    public Usuario() { }

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.artistasFavoritos = new ArrayList<>();
    }

    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "senha")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @ManyToMany
    @JoinTable(name = "tb_artistas_favoritos")
    public List<Artista> getArtistasFavoritos() {
        return artistasFavoritos;
    }

    public void setArtistasFavoritos(List<Artista> artistasFavoritos) {
        this.artistasFavoritos = artistasFavoritos;
    }

    public void adicionarArtistaFavoritos(Artista artista) { this.artistasFavoritos.add(artista); }

    public void removerArtistaFavoritos(Artista artista) { this.artistasFavoritos.remove(artista); }

    public boolean ehArtistaFavorito(Artista artista) { return this.artistasFavoritos.contains(artista);}

}
