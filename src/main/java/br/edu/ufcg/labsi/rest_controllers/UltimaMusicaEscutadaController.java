package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.Musica;
import br.edu.ufcg.labsi.entidades.UltimaMusicaEscutada;
import br.edu.ufcg.labsi.entidades.Usuario;
import br.edu.ufcg.labsi.repositorios.ArtistasRepository;
import br.edu.ufcg.labsi.repositorios.MusicasRepository;
import br.edu.ufcg.labsi.repositorios.UltimaMusicaEscutadaRepository;
import br.edu.ufcg.labsi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ultima-musica")
public class UltimaMusicaEscutadaController {

    @Autowired
    private UltimaMusicaEscutadaRepository ultimaMusicaEscutadaRepository;
    @Autowired
    private MusicasRepository musicasRepository;
    @Autowired
    private ArtistasRepository artistasRepository;
    @Autowired
    private Util util = new Util();

    @PostMapping("/{idMusica}/artista/{idArtista}")
    public ResponseEntity<Musica> adicionarUltimaMusicaEscutada(@RequestHeader("Authorization") String autorizacao,
                                                                  @PathVariable Integer idMusica,
                                                                  @PathVariable Integer idArtista) {
        Musica musica = this.musicasRepository.getMusicaById(idMusica);
        Artista artista = this.artistasRepository.getArtistaById(idArtista);
        Usuario usuario = this.util.getUsuario(autorizacao);
        UltimaMusicaEscutada ultimaMusicaEscutada = this.ultimaMusicaEscutadaRepository.
                getUltimaMusicaEscutadaByArtistaAndUsuario(artista, usuario);

        if (ultimaMusicaEscutada == null) {
            ultimaMusicaEscutada = new UltimaMusicaEscutada(usuario, musica, artista);
            this.ultimaMusicaEscutadaRepository.save(ultimaMusicaEscutada);
        }

        return new ResponseEntity<>(musica, HttpStatus.OK);
    }

    @GetMapping("/{idArtista}")
    public ResponseEntity<Musica> pegarUltimaMusicaEscutada(@RequestHeader("Authorization") String autorizacao,
                                                            @PathVariable Integer idArtista) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Artista artista = this.artistasRepository.getArtistaById(idArtista);
        UltimaMusicaEscutada ultimaMusicaEscutada = this.ultimaMusicaEscutadaRepository.
                getUltimaMusicaEscutadaByArtistaAndUsuario(artista, usuario);
        Musica musica;
        if (ultimaMusicaEscutada == null) {
            musica = null;
        } else {
            musica = ultimaMusicaEscutada.getMusica();
        }

        return new ResponseEntity<>(musica, HttpStatus.OK);
    }

}
