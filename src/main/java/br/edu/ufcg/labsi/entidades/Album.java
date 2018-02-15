package br.edu.ufcg.labsi.entidades;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "tb_albums")
public class Album {

    private int id;
    private String nome;
    @Autowired
    private Artista artista;

    public Album() { }

    public Album(String nome, Artista artista) {
        this.nome = nome;
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

    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "artista")
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
