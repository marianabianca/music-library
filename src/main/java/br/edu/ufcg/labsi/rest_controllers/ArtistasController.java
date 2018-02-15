package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.Nota;
import br.edu.ufcg.labsi.entidades.Usuario;
import br.edu.ufcg.labsi.repositorios.ArtistasRepository;
import br.edu.ufcg.labsi.repositorios.UsuarioRepository;
import br.edu.ufcg.labsi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artistas")
public class ArtistasController {

    @Autowired
    private ArtistasRepository artistasRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Util util = new Util();

    @PostMapping
    public ResponseEntity<Artista> cadastraArtista(@RequestBody Artista artista) {
        String nome = artista.getNome();

        if (this.artistasRepository.existsArtistaByNome(nome)) {
            return new ResponseEntity<>(artista, HttpStatus.CONFLICT);
        }

        artista = this.artistasRepository.save(artista);

        return new ResponseEntity<>(artista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Artista pegaArtista(@PathVariable Integer id) {
        Artista artista = this.artistasRepository.getArtistaById(id);
        return artista;
    }

    @GetMapping
    public Iterable<Artista> pegaArtistas() {
        Iterable<Artista> artistas = this.artistasRepository.findAll();
        return artistas;
    }

    @GetMapping("/eh-favorito/{id}")
    public boolean ehFavorito(@PathVariable Integer id,
                              @RequestHeader("Authorization") String autorizacao) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Artista artista = this.artistasRepository.getArtistaById(id);
        boolean ehFavorito = usuario.ehArtistaFavorito(artista);
        return ehFavorito;
    }

    @PostMapping("/favoritos/{id}")
    public ResponseEntity<Usuario> adicionarArtistaFavorito(@PathVariable Integer id,
                                                            @RequestHeader("Authorization") String autorizacao) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Artista artista = this.artistasRepository.getArtistaById(id);
        usuario.adicionarArtistaFavoritos(artista);
        usuario = this.usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/favoritos/{id}")
    public Iterable<Artista> removerArtistaFavorito(@PathVariable Integer id,
                                       @RequestHeader("Authorization") String autorizacao) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Artista artista = this.artistasRepository.getArtistaById(id);
        usuario.removerArtistaFavoritos(artista);
        usuario = this.usuarioRepository.save(usuario);
        Iterable<Artista> artistas = usuario.getArtistasFavoritos();
        return artistas;
    }

    @GetMapping("/favoritos")
    public Iterable<Artista> pegarArtistasFavoritos(@RequestHeader("Authorization") String autorizacao) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Iterable<Artista> artistas = usuario.getArtistasFavoritos();
        return artistas;
    }

}
