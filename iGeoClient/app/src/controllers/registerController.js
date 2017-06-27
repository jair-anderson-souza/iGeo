var app = angular.module("app");
app.controller("registerController", function($scope, userServiceAPI){
	$scope.register = function(newUser){
		userServiceAPI.register(newUser).then(function (response) {
            console.log("Deu certo");
        }), function (response) {
            console.log("Não deu certo");
        };
	};
});