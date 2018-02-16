angular.module("lab1").factory("tokenInterceptor", function($rootScope, $window, $state, $q){
    return {
        'request': function($config){
            const token = $window.sessionStorage.token;
            if (token) {
                $config.headers['Authorization'] = token;
            }
            return $config;
//            config.headers['Authorization'] = "Bearer " + localStorage.getItem("userToken");
//            return config;
        }
    };
});