package br.edu.ufcg.labsi.repositorios;

import br.edu.ufcg.labsi.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public boolean existsUsuarioByLogin(String login);
    public Usuario getUsuarioByLogin(String login);

}
