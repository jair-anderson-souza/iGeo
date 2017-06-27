var app = angular.module("app");
app.controller("registerController", function($scope, $http, apiConfig){
	$scope.register = function(newUser){
		$http.post(apiConfig.api + "/register", newUser).then(function (response) {
            console.log("Deu certo");
        }), function (response) {
            console.log("Não deu certo");
        };
	};
});