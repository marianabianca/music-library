angular.module("lab1").controller("artistasFavoritosCtrl", function ($scope, $state, artistasFavoritosService) {
    
    $scope.artistasFavoritos;
    
    artistasFavoritosService.getArtistasFavoritos()
    .then(function (response) {
        $scope.artistasFavoritos = response.data;
    })

    $scope.excluirArtista = function (idArtista) {
        artistasFavoritosService.excluirArtista(idArtista)
        .then(function (response) {
        $scope.artistasFavoritos = response.data;
        })  
    }

});