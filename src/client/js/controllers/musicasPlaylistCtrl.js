angular.module("lab1").controller("musicasPlaylistCtrl", function ($scope, $state, playlist, playlistsService) {

    $scope.idPlaylist;
    $scope.idPlaylist = playlist.id;
    $scope.nomePlaylist = playlist.nome;
    $scope.musicas = playlist.musicas;
    
    playlistsService.getPlaylist(playlist)
    .then(function (response){
        $scope.idPlaylist = response.data.id;
        $scope.nomePlaylist = response.data.nome;
        $scope.musicas = response.data.musicas;
    })

    $scope.excluirMusica = function (idPlaylist, nomeMusica) {
        playlistsService.excluirMusicaDaPlaylist(idPlaylist, nomeMusica)
        .then (function (response) {
            $scope.musicas = response.data;
        })
    }

});