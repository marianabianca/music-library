angular.module("lab1").factory("musicasService", function (albunsService, $http) {
    
    var musicas = [];
    
    var albuns = albunsService.albuns;
    var adicionarAlbum = albunsService.adicionarAlbum;
    
    var adicionarMusicaAoAlbum = function (musica, nomeAlbum) {
        var album = albuns[nomeAlbum];
        var musicasDoAlbum = album.musicas;
        var nomeDaMusica = musica.nome;
        
        if (!(nomeDaMusica in musicasDoAlbum)) {
            musicasDoAlbum[nomeDaMusica] = musica;
            musicas.push(musica);
            return false;
        } else {
        }
    };
    
    var adicionarMusicaAoSistema = function (musica) {
        return $http.post('/musicas', musica);
    };
    
    var getMusicas = function () {
        return $http.get('/musicas');
    }
    
    return {
        musicas: musicas,
        adicionarMusicaAoAlbum: adicionarMusicaAoAlbum,
        adicionarMusicaAoSistema: adicionarMusicaAoSistema,
        getMusicas: getMusicas
    };
    
});