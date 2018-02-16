angular.module("lab1").controller("artistaCtrl", function ($scope, $state, artista, albunsService, artistasFavoritosService, $http) {
    
    $scope.artista = artista;
    $scope.albunsDoArtista = albunsService.getAlbunsDoArtista(artista.nome);
    $scope.favoritos = artistasFavoritosService.artistasFavoritos;
    $scope.ehFavorito;
    $scope.musica;
    $scope.nota;
    $scope.musicas;
    
    $http.get('http://localhost:8080/artistas/' + artista)
    .then(function (response) {
        $scope.artista = response.data;
    })
    
    $http.get('http://localhost:8080/ultima-musica/' + artista)
    .then(function (response) {
        $scope.musica = response.data;
    })
    
    $http.get('http://localhost:8080/albuns/' + artista)
    .then(function (response) {
        $scope.albunsDoArtista = response.data;
    })
    
    $http.get('http://localhost:8080/artistas/eh-favorito/' + artista)
    .then(function (response){
        $scope.ehFavorito = response.data;
    })
    
    $http.get('http://localhost:8080/nota/artista/' + artista)
    .then(function (response){
        $scope.nota = response.data;
    })
    
    $http.get('http://localhost:8080/musicas/artista/' + artista)
    .then(function (response){
        $scope.musicas = response.data;
    })
    
    $http.get('http://localhost:8080/ultima-musica/' + artista)
    .then(function (response){
        $scope.musica = response.data;
    })
    
    $scope.adicionarNotaAoArtista = function (notaDada) {
        $scope.nota = notaDada;
        delete $scope.notaDada;
        $http.post('http://localhost:8080/nota/' + notaDada + '/artista/' + artista);
    }
    
    $scope.adicionarUltimaMusicaEscutada = function (musicaDada) {
        $scope.musica = musicaDada;
        delete $scope.musicaDada;
        $http.post('http://localhost:8080/ultima-musica/' + musicaDada.id + '/artista/' + artista);
    }
    
    $scope.adicionarArtistaAosFavoritos = function () {
        $http.post('http://localhost:8080/artistas/favoritos/' + $scope.artista.id);
        $scope.ehFavorito = true;
    };

    $scope.excluirArtistaDosFavoritos = function () {
        $http.delete('http://localhost:8080/artistas/favoritos/' + $scope.artista.id);
        $scope.ehFavorito = false;
    }
    
});