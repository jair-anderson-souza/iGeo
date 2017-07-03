var app = angular.module("app");

app.controller("loginController", function ($scope, userServiceAPI, $rootScope, $location, $state, $http) {
    
    $scope.login = function (loginVO) {
        userServiceAPI.login(loginVO).then(function (response) {
        	$rootScope.token = response.data;
        	console.log("Ctrl : " +$rootScope.token);
        	$location.path("/home");
        }), function (response) {
        	$location.path("/index");
            console.log("Não deu certo");
        /*
        */
        };
    };
});