var app = angular.module("app");
app.controller("registerController", function($scope, userServiceAPI, $location, $state){
	
	$scope.genders = ["MALE", "FEMALE"];

	$scope.message = {};

	$scope.goToLoginPage = function(){
		$state.go("/login");
	};

	$scope.register = function(newUser){
		userServiceAPI.register(newUser).then(function (response) {
            if(response.status === 204){
            	$scope.message.messageRegisteredError = "Ocorreu um erro, tente novamente mais tarde";
            }else {
				delete $scope.newUser;
            	delete $scope.formRegister;
            	$scope.message.userRegistered = "Seu cadastro foi efetuado com sucesso!";
            }
        }), function (response) {
            console.log("Não deu certo");
        };
	};
});