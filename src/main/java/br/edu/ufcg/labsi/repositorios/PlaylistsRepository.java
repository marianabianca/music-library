package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Playlist;
import br.edu.ufcg.labsi.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistsRepository extends CrudRepository<Playlist, Integer> {

    public boolean existsPlaylistByNomeAndAndUsuario(String nome, Usuario usuario);
    public Iterable<Playlist> getPlaylistsByUsuario(Usuario usuario);
    public Playlist getPlaylistById(Integer id);
}
