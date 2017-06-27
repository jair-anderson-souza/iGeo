var app = angular.module("app");

app.controller("loginController", function ($scope, $http, apiConfig) {
    $scope.login = function (loginVO) {
        $http.post(apiConfig.api + "/login", loginVO).then(function (response) {
            console.log("Deu certo");
        }), function (response) {
            console.log("Não deu certo");
        };
    };
});