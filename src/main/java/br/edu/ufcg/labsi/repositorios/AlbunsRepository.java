package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Album;
import br.edu.ufcg.labsi.entidades.Artista;
import org.springframework.data.repository.CrudRepository;

public interface AlbunsRepository extends CrudRepository<Album, Integer> {

    public boolean existsAlbumByNome(String nome);
    public Album getAlbumByNomeAndArtista(String nome, Artista artista);
    public Iterable<Album> getAlbumsByArtista_Id(int id);

}
