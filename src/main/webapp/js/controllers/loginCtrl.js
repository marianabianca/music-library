angular.module("lab1").controller("loginCtrl", function ($scope, $http, $location, $window) {
    
    $scope.token;
    $scope.erroLogin;
    $scope.erroCadastro;
    
    $scope.cadastrar = function (usuario) {
        $http.post("http://localhost:8080/cadastra", usuario).then(function(reponse) {
                $scope.erroCadastro = null;
            }).catch(function(response) {
                $scope.erroCadastro = response.data.message;
            })
        
        delete $scope.usuarioCadastro;
    }
    
    $scope.autenticar = function (usuario) {
        $http.post("http://localhost:8080/autenticar", usuario).then(function(response){
            $scope.token = response.data.token;
            $window.sessionStorage.token = $scope.token;
            $scope.erroLogin = null;
            $location.path("/home");
        }).catch(function(response){
            $scope.erroLogin = response.data.message;
        })
        
        delete $scope.usuarioLogin;
    }
    
    $scope.logout = function () {
        $window.sessionStorage.token = null;
        $location.path("/login");
    }
})