var app = angular.module("app");
app.controller("registerController", function($scope, userServiceAPI){
	
	$scope.genders = ["MALE", "FEMALE"];
	
	$scope.register = function(newUser){
		userServiceAPI.register(newUser).then(function (response) {
            console.log("Deu certo");
            console.log(response.data);
        }), function (response) {
            console.log("Não deu certo");
        };
	};
});