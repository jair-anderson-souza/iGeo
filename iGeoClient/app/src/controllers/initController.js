var app = angular.module("app");
app.controller("initController", function($scope, $http){
	$scope.login = function(loginVO){
		$http.post("http://localhost:8080/iGeoWebServices/webresources/login", loginVO).then(function(response){
			console.log("Deu certo");
		}), function(response){
			console.log("Não deu certo");
		};
	};
});