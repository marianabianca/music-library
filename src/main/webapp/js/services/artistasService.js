angular.module("lab1").factory("artistasService", function (albunsService, $http) {
    
    var artistas = [];
    
    var temArtista = function (nomeArtista) {
        for (var artista in artistas) {
            var nomeArtistaAux = artistas[artista].nome;
            if (nomeArtistaAux == nomeArtista) {
                return true;
            }
        }
        
        return false;
    };
    
    var adicionarArtistaAoSistema = function (artista) {
        return $http.post('/artistas', artista)
    };
    
    var getArtista = function (idArtista) {
        return $http.get('/artistas/' + idArtista);
    };
    
    var getArtistas = function () {
        return $http.get('/artistas');
    }
    
    return {
        artistas: artistas,
        adicionarArtistaAoSistema: adicionarArtistaAoSistema,
        getArtista: getArtista,
        getArtistas: getArtistas
    }
    
});