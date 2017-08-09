var app = angular.module("app");

app.controller("loginController", function ($scope, userServiceAPI, $rootScope, $state, $http) {

    $scope.message = {};

    $scope.login = function (loginVO) {
        userServiceAPI.login(loginVO).then(function (response) {
            if(response.status === 200){
                $rootScope.token = response.data;
                $state.go("/home");
                delete $scope.message;
            }else if(response.status === 204){
                $scope.message.messageLoginError = "Os dados estão inválidos, tente novamente";
            }
        }), function (response) {

        };
    };

    $scope.logout = function(){
        console.log("Sair");
    };


});