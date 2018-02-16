angular.module("lab1").service("albunsService", function (albumFactory) {
    var albuns = {};
    
    var criarAlbum = albumFactory.criarAlbum;
    
    var temAlbum = function (nomeAlbum) {
        return nomeAlbum in albuns;
    };
    
    var adicionarAlbum = function (nomeAlbum, nomeArtista) {
        var album = criarAlbum(nomeAlbum, nomeArtista);
        
        if (!temAlbum(nomeAlbum)) {
            var copiaAlbum = angular.copy(album);
            albuns[nomeAlbum] = copiaAlbum;
        };
    };
    
    var getAlbunsDoArtista = function (nomeArtista) {
        albunsDoArtista = [];
        
        for (var i in albuns) {
            var album = albuns[i];
            
            if (album.artista == nomeArtista) {
                albunsDoArtista.push(album);
            }
        }
        
        return albunsDoArtista;
    };

    return {
        albuns: albuns,
        criarAlbum: criarAlbum,
        adicionarAlbum: adicionarAlbum,
        getAlbunsDoArtista: getAlbunsDoArtista
    };
    
});