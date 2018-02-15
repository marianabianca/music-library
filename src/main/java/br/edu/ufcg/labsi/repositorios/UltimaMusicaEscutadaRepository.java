package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.UltimaMusicaEscutada;
import br.edu.ufcg.labsi.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UltimaMusicaEscutadaRepository extends CrudRepository<UltimaMusicaEscutada, Integer> {

    public UltimaMusicaEscutada getUltimaMusicaEscutadaByArtistaAndUsuario(Artista artista, Usuario usuario);

}
