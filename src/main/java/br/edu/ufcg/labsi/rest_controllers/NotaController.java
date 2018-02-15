package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Artista;
import br.edu.ufcg.labsi.entidades.Nota;
import br.edu.ufcg.labsi.entidades.Usuario;
import br.edu.ufcg.labsi.repositorios.ArtistasRepository;
import br.edu.ufcg.labsi.repositorios.NotaRepository;
import br.edu.ufcg.labsi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private ArtistasRepository artistasRepository;
    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private Util util = new Util();

    @PostMapping("{nota}/artista/{id}")
    public ResponseEntity<String> adicionarNotaArtista(@RequestHeader("Authorization") String autorizacao,
                                                     @PathVariable Integer id,
                                                     @PathVariable String nota) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Artista artista = this.artistasRepository.getArtistaById(id);

        Nota notaObj;
        if (this.notaRepository.getNotaByArtistaAndUsuario(artista, usuario) != null) {
            notaObj = this.notaRepository.getNotaByArtistaAndUsuario(artista, usuario);
            notaObj.setNota(nota);
        } else {
            notaObj = new Nota(nota, artista, usuario);
        }

        notaObj = this.notaRepository.save(notaObj);

        return new ResponseEntity<>(nota, HttpStatus.OK);
    }

    @GetMapping("/artista/{id}")
    public String pegarNota(@RequestHeader("Authorization") String autorizacao,
                                          @PathVariable Integer id) {
        Usuario usuario = this.util.getUsuario(autorizacao);
        Artista artista = this.artistasRepository.getArtistaById(id);
        Nota nota = this.notaRepository.getNotaByArtistaAndUsuario(artista, usuario);

        if (nota == null) {
            return null;
        }

        return nota.getNota();
    }
}
