var app = angular.module("app");

app.controller("loginController", function ($scope, userServiceAPI) {
    $scope.login = function (loginVO) {
        userServiceAPI.login(loginVO).then(function (response) {
            console.log("Deu certo");
            console.log(response.data);
        }), function (response) {
            console.log("Não deu certo");
        };
    };
});