package br.edu.ufcg.labsi.entidades;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "tb_notas")
public class Nota {

    private int id;
    private String nota;
    @Autowired
    private Artista artista;
    @Autowired
    private Usuario usuario;

    public Nota() { }

    public Nota(String nota, Artista artista, Usuario usuario) {
        this.nota = nota;
        this.artista = artista;
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nota")
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
    @JoinColumn(name = "usuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
