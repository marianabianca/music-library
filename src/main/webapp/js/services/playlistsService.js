angular.module("lab1").factory("playlistsService", function ($http) {
    
    var playlists = [];
    
    var temPlaylist = function(nomePlaylist) {
        for (var index = 0; index < playlists.length; index++) {
            var playlist = playlists[index];
            if (playlist.nome == nomePlaylist) {
                return true;
            }
        }

        return false;
    }

    var criarPlaylist = function (nomePlaylist) {
        return $http.post('http://localhost:8080/playlists', nomePlaylist);
    };

    var temMusica = function (musica, playlist) {
        var musicasDaPlaylist = playlist.musicas;
        for (var index = 0; index < musicasDaPlaylist.length; index++) {
            var musicaAux = musicasDaPlaylist[index];
            if (musicaAux == musica) {
                return true;
            }            
        }

        return false;
    }

    var adicionarMusicaAPlaylist = function (musica, playlist) {
        $http.post('http://localhost:8080/playlists/' + playlist.id + '/adicionar-musica/' + musica.id);
    }

    var getPlaylist = function (idPlaylist) {        
        return $http.get('http://localhost:8080/playlists/' + idPlaylist);
    }

    var getMusicas = function (nomePlaylist) {
        var playlist = getPlaylist(nomePlaylist);
        var musicasDaPlaylist = playlist.musicas;
        return musicasDaPlaylist;
    }

    var excluirPlaylist = function (playlistAux) {        
        return $http.delete('http://localhost:8080/playlists/' + playlistAux.id);
    }

    var excluirMusicaDaPlaylist = function (idPlaylist, idMusica) {  
        return $http.delete('http://localhost:8080/playlists/' + idPlaylist + '/remover-musica/' + idMusica);
    }
    
    var getPlaylists = function () {
        return $http.get('http://localhost:8080/playlists');
    }

    return {
        playlists: playlists,
        criarPlaylist: criarPlaylist,
        adicionarMusicaAPlaylist: adicionarMusicaAPlaylist,
        getPlaylist: getPlaylist,
        excluirPlaylist: excluirPlaylist,
        excluirMusicaDaPlaylist: excluirMusicaDaPlaylist,
        getPlaylists: getPlaylists
    }
    
});