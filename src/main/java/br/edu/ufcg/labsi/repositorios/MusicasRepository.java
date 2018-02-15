package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Album;
import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.Musica;
import org.springframework.data.repository.CrudRepository;

public interface MusicasRepository extends CrudRepository<Musica, Integer> {

    public boolean existsMusicaByNomeAndAlbum(String nome, Album album);
    public Musica getMusicaById(Integer id);
    public Iterable<Musica> getMusicasByArtista(Artista artista);

}
