package br.edu.ufcg.labsi.util;

import br.edu.ufcg.labsi.entidades.Usuario;
import br.edu.ufcg.labsi.repositorios.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Util {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario getUsuario(String autorizacao) {
        String login = this.getLogin(autorizacao);
        Usuario usuario = this.usuarioRepository.getUsuarioByLogin(login);

        return usuario;
    }

    public String getLogin(String autorizacao) {
        String login = (String) Jwts.parser().setSigningKey("chocolate").parseClaimsJws(autorizacao).getBody().get("login");
        return login;
    }

}
