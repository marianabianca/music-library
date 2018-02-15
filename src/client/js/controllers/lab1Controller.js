angular.module("lab1").controller("lab1Ctrl", function ($scope, musicasService, artistasService, albunsService, playlistsService, $window, $location) {
    
    $scope.artistas = artistasService.artistas;
    $scope.albuns = albunsService.albuns;
    $scope.musicas = musicasService.musicas;
    $scope.playlists = playlistsService.playlists;
    
    artistasService.getArtistas()
    .then(function(response) {
        $scope.artistas = response.data;
    })
    
    musicasService.getMusicas()
    .then(function(response) {
        $scope.musicas = response.data;
    })
    
    playlistsService.getPlaylists()
    .then(function(response) {
        $scope.playlists = response.data;
    })

    $scope.filtro = "";
    $scope.filtroMusica = "";
    
    $scope.artistaJaExiste = false;
    $scope.musicaJaExiste = false;
    $scope.playlistJaExiste = false;
    
    $scope.fazerBusca = function (caracteristica) {
        $scope.filtro = caracteristica;
        delete $scope.caracteristica;
    };
    
    $scope.adicionarArtistaAoSistema = function (artista) {
        artistasService.adicionarArtistaAoSistema(artista)
        .then(function (response) {
            $scope.artistaJaExiste = false;
            $scope.artistas.push(response.data);
        })
        .catch(function (response) {
            $scope.artistaJaExiste = true;
        })
        delete $scope.artistaCriado;
    };
    
    $scope.adicionarMusicaAoSistema = function (musica) {
        musicasService.adicionarMusicaAoSistema(musica)
        .then(function (response) {
            $scope.musicaJaExiste = false;
            $scope.musicas.push(response.data);
        })
        .catch(function (response) {
            $scope.musicaJaExiste = true;
        })
        
        delete $scope.musica;
    };
    
    $scope.mostrarArtista = function (artista) {
        console.log(artista.nome);
    };
    
    $scope.fazerBuscaMusica = function (caracteristicaMusica) {
        $scope.filtroMusica = caracteristicaMusica;
        delete $scope.caracteristicaMusica;
    }

    $scope.criarPlaylist = function (nomePlaylist) {
        playlistsService.criarPlaylist(nomePlaylist)
        .then(function (response) {
            $scope.playlistJaExiste = false;
            $scope.playlists.push(response.data);
        })
        .catch(function (response) {
            $scope.playlistJaExiste = true;
        })

        delete $scope.playlist;
    }

    $scope.adicionarMusicaAPlaylist = function (musica, playlist) {
        playlistsService.adicionarMusicaAPlaylist(musica, playlist);
    }
    
    $scope.logout = function () {
        $window.sessionStorage.token = null;
        $location.path("/login");
    }
    
});