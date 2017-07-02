var app = angular.module("app");

app.controller("loginController", function ($scope, $rootScope, $location, $state) {
    $scope.ir = function(){
        $state.go("login");
    };
    
});