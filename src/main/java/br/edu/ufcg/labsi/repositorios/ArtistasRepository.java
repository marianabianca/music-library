package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Artista;
import org.springframework.data.repository.CrudRepository;

public interface ArtistasRepository extends CrudRepository<Artista, Integer> {

    public boolean existsArtistaByNome(String nome);
    public Artista getArtistaById(Integer id);

}
