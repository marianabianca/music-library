package br.edu.ufcg.labsi.entidades;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "tb_musicas")
public class Musica {

    private int id;
    private String nome;
    private String anoDeLancamento;
    private String duracao;
    @Autowired
    private Artista artista;
    @Autowired
    private Album album;

    public Musica() { }

    public Musica(String nome, String anoDeLancamento, String duracao, Artista artista, Album album) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.artista = artista;
        this.album = album;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "ano_de_lancamento")
    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(String anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    @Column(name = "duracao")
    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @ManyToOne
    @JoinColumn(name = "artista")
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @ManyToOne
    @JoinColumn(name = "album")
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
