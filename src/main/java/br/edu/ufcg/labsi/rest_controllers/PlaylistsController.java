package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Musica;
import br.edu.ufcg.labsi.entidades.Playlist;
import br.edu.ufcg.labsi.entidades.Usuario;
import br.edu.ufcg.labsi.repositorios.MusicasRepository;
import br.edu.ufcg.labsi.repositorios.PlaylistsRepository;
import br.edu.ufcg.labsi.repositorios.UsuarioRepository;
import br.edu.ufcg.labsi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/playlists")
public class PlaylistsController {

    @Autowired
    private PlaylistsRepository playlistsRepository;
    @Autowired
    private MusicasRepository musicasRepository;
    @Autowired
    private Util util = new Util();

    @PostMapping
    public ResponseEntity<Playlist> cadastraPlaylist(@RequestBody Playlist playlist,
                                           @RequestHeader("Authorization") String autorizacao) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        String nomePlaylist = playlist.getNome();

        if (this.playlistsRepository.existsPlaylistByNomeAndAndUsuario(nomePlaylist, usuario)) {
            return new ResponseEntity<>(playlist, HttpStatus.CONFLICT);
        }

        playlist.setUsuario(usuario);
        playlist = playlistsRepository.save(playlist);

        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public Iterable<Playlist> deletarPlaylist(@PathVariable Integer id) {
        this.playlistsRepository.delete(id);
        Iterable<Playlist> playlists = this.playlistsRepository.findAll();
        return playlists;
    }

    @GetMapping()
    public Iterable<Playlist> pegarPlaylists(@RequestHeader("Authorization") String autorizacao) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Iterable<Playlist> playlists = this.playlistsRepository.getPlaylistsByUsuario(usuario);
        return playlists;
    }

    @GetMapping("/{id}")
    public Playlist pegarPlaylist(@PathVariable Integer id) {
        Playlist playlist = this.playlistsRepository.getPlaylistById(id);
        return playlist;
    }

    @PostMapping("{idPlaylist}/adicionar-musica/{idMusica}")
    public ResponseEntity<Playlist> adicionarMusicaPlaylist(@PathVariable Integer idPlaylist,
                                                            @PathVariable Integer idMusica) {
        Playlist playlist = this.playlistsRepository.getPlaylistById(idPlaylist);
        Musica musica = this.musicasRepository.getMusicaById(idMusica);

        if (playlist.temMusica(musica)) {
            return new ResponseEntity<>(playlist, HttpStatus.CONFLICT);
        }

        playlist.adicionarMusica(musica);
        playlist = this.playlistsRepository.save(playlist);

        return new ResponseEntity<>(playlist, HttpStatus.OK);
    }

    @DeleteMapping("{idPlaylist}/remover-musica/{idMusica}")
    public Iterable<Musica> removerMusicaPlaylist(@PathVariable Integer idPlaylist,
                                      @PathVariable Integer idMusica) {
        Playlist playlist = this.playlistsRepository.getPlaylistById(idPlaylist);
        Musica musica = this.musicasRepository.getMusicaById(idMusica);

        playlist.removerMusica(musica);
        playlist = this.playlistsRepository.save(playlist);
        Iterable<Musica> musicasPlaylist = playlist.getMusicas();
        return musicasPlaylist;
    }

    @GetMapping("musicas/{idPlaylist}")
    public Iterable<Musica> pegarMusicasPlaylist(@PathVariable Integer idPlaylist) {
        Playlist playlist = this.playlistsRepository.getPlaylistById(idPlaylist);
        Iterable<Musica> musicas = playlist.getMusicas();

        return musicas;
    }

}
