package br.edu.ufcg.labsi.rest_controllers;

import br.edu.ufcg.labsi.entidades.Album;
import br.edu.ufcg.labsi.repositorios.AlbunsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albuns")
public class AlbunsController {

    @Autowired
    AlbunsRepository albunsRepository;

    @GetMapping("/{id}")
    public Iterable<Album> pegarAlbunsArtista(@PathVariable int id) {
        Iterable<Album> albuns = this.albunsRepository.getAlbumsByArtista_Id(id);

        return albuns;
    }

}
