package br.edu.ufcg.labsi.entidades;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_playlists")
public class Playlist {

    private int id;
    private String nome;
    @Autowired
    private List<Musica> musicas;
    @Autowired
    private Usuario usuario;

    public Playlist() { }

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToMany
    @JoinTable(name = "tb_musicas_playlist")
    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public void adicionarMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        this.musicas.remove(musica);
    }

    public boolean temMusica(Musica musica) {
        return this.musicas.contains(musica);
    }

    @ManyToOne
    @JoinColumn(name = "usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}