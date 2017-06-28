/*
var app = angular.module("app");
app.factory('AuthInterceptor', AuthInterceptor).config(function ($httpProvider) {
    $httpProvider.interceptors.push('AuthInterceptor');
});

function AuthInterceptor($location, AuthService, $q) {
    console.log("entrou");
    return {
        request: function (config) {
            config.headers = config.headers || {};

            if (AuthService.getToken()) {
                config.headers['SigningKey'] = AuthService.getToken();
            }
            return config;
        },

        responseError: function (response) {
            if (response.status === 401 || response.status === 403) {
                $location.path('/');
            }
            return $q.reject(response);
        }
    };
}

app.factory('AuthService', AuthService);
function AuthService($http, $q) {
    console.log("entrou");
    return {
        getToken: function () {
            return $http.defaults.headers.common['SigningKey'] = token;;
        },
        setToken: function (token) {
            $http.defaults.headers.common['SigningKey'] = token;
        },
        signin: function (data) {
            $http.post('data', data);
        },
        signup: function (data) {
            $http.post('data', data);
        },
        logout: function (data) {
            delete $http.defaults.headers.common['SigningKey'];
            $q.when();
        }
    };
}*/