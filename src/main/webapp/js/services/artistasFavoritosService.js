angular.module("lab1").factory("artistasFavoritosService", function ($http) {
   
    var artistasFavoritos = [];
    
    var temArtista = function (artista) {
      return artistasFavoritos.includes(artista);  
    };
    
    var adicionarArtistaAosFavoritos = function (artista) {
        if (!temArtista(artista)) {
            artista.ehFavorito = true;
            artistasFavoritos.push(artista);
        }
    };

    var excluirArtista = function (idArtista) {  
        return $http.delete('/artistas/favoritos/' + idArtista);
    }
    
    var getArtistasFavoritos = function () {
        return $http.get('/artistas/favoritos');
    }
    
    return {
        artistasFavoritos: artistasFavoritos,
        adicionarArtistaAosFavoritos: adicionarArtistaAosFavoritos,
        excluirArtista: excluirArtista,
        getArtistasFavoritos: getArtistasFavoritos
    };
    
});