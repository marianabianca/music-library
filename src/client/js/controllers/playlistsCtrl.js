angular.module("lab1").controller("playlistsCtrl", function ($scope, $state, playlistsService, $http) {

    $scope.playlists;
    
    playlistsService.getPlaylists()
    .then(function (response) {
        $scope.playlists = response.data;
    })

    $scope.excluirPlaylist = function (playlist) {
        playlistsService.excluirPlaylist(playlist)
        .then(function (response) {
        $scope.playlists = response.data;
        })  
    }

});