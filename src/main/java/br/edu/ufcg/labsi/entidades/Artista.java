package br.edu.ufcg.labsi.entidades;

import javax.persistence.*;

@Entity
@Table(name = "tb_artistas")
public class Artista {

    private int id;
    private String nome;
    private String imagem;

    public Artista() { }

    public Artista(String nome, String imagem) {
        this.nome = nome;
        this.imagem = imagem;
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

    @Column(name = "imagem")
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
