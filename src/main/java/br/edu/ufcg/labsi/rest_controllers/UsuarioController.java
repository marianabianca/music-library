package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Usuario;
import br.edu.ufcg.labsi.repositorios.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastra")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario){
        String login = usuario.getLogin();

        if (this.usuarioRepository.existsUsuarioByLogin(login)) {
            return new ResponseEntity<>(usuario, HttpStatus.CONFLICT);
        }

        usuario = this.usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/autenticar")
    public Token autenticarUsuario(@RequestBody Usuario usuario) throws ServletException {
        String login = usuario.getLogin();
        String senha = usuario.getSenha();

        if (!this.usuarioRepository.existsUsuarioByLogin(login) ||
                !this.usuarioRepository.getUsuarioByLogin(login).getSenha().equals(senha)) {
            throw new ServletException("usuario ou senha invalidos");
        }

        Token token = this.gerarToken(login);
        return token;
    }

    private Token gerarToken(String login) {
        String tokenString = Jwts.builder()
                .setSubject(login)
                .claim("login", login)
                .signWith(SignatureAlgorithm.HS512, "chocolate")
                .compact();

        Token token = new Token(tokenString);

        return token;
    }

    @PostMapping("/teste")
    public Object teste(@RequestHeader("Authorization") String autoriza) {
        String token = autoriza.substring(7);
        String login = (String) Jwts.parser().setSigningKey("chocolate").parseClaimsJws(token).getBody().get("login");

        System.out.println("login " + login);

        return null;
    }

    private class Token{
        String token;

        public Token(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
