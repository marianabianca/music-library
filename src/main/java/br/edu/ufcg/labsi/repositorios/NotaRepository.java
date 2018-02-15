package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.Nota;
import br.edu.ufcg.labsi.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface NotaRepository extends CrudRepository<Nota, Integer> {

    public Nota getNotaByArtistaAndUsuario(Artista artista, Usuario usuario);

}
