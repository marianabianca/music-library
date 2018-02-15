package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Album;
import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.Musica;
import br.edu.ufcg.labsi.repositorios.AlbunsRepository;
import br.edu.ufcg.labsi.repositorios.ArtistasRepository;
import br.edu.ufcg.labsi.repositorios.MusicasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/musicas")
public class MusicasController {

    @Autowired
    private MusicasRepository musicasRepository;
    @Autowired
    private AlbunsRepository albunsRepository;
    @Autowired
    private ArtistasRepository artistasRepository;

    @PostMapping
    public ResponseEntity<Musica> cadastrarMusica(@RequestBody Musica musica) {
        Album album = musica.getAlbum();
        String nomeAlbum = album.getNome();
        Artista artista = this.artistasRepository.getArtistaById(musica.getArtista().getId());

        if (this.albunsRepository.existsAlbumByNome(nomeAlbum)) {
            album = this.albunsRepository.getAlbumByNomeAndArtista(album.getNome(), artista);
            if (this.musicasRepository.existsMusicaByNomeAndAlbum(musica.getNome(), album)) {
                return new ResponseEntity<>(musica, HttpStatus.CONFLICT);
            }
        } else {
            album.setArtista(artista);
            album = this.albunsRepository.save(album);
        }

        musica.setAlbum(album);
        musica.setArtista(artista);
        musica = this.musicasRepository.save(musica);

        return new ResponseEntity<>(musica, HttpStatus.OK);
    }

    @GetMapping
    public Iterable<Musica> pegarMusicas() {
        Iterable<Musica> musicas = this.musicasRepository.findAll();

        return musicas;
    }

    @GetMapping("/artista/{idArtista}")
    public Iterable<Musica> pegarMusicasArtista(@PathVariable Integer idArtista) {
        Artista artista = this.artistasRepository.getArtistaById(idArtista);
        Iterable<Musica> musicas = this.musicasRepository.getMusicasByArtista(artista);
        return musicas;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Musica> pegarMusica(@PathVariable Integer id) {
        Musica musica = this.musicasRepository.getMusicaById(id);

        return new ResponseEntity<>(musica, HttpStatus.OK);
    }



}
