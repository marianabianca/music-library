package br.edu.ufcg.labsi.entidades;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "tb_ultimas_musicas_escutadas")
public class UltimaMusicaEscutada {

    private int id;
    @Autowired
    private Musica musica;
    @Autowired
    private Usuario usuario;
    @Autowired
    private Artista artista;

    public UltimaMusicaEscutada() { }

    public UltimaMusicaEscutada(Usuario usuario, Musica musica, Artista artista) {
        this.usuario = usuario;
        this.musica = musica;
        this.artista = artista;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "musica")
    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    @ManyToOne
    @JoinColumn(name = "usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "artista")
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
