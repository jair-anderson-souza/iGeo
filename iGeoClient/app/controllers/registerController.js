var app = angular.module("app");
app.controller("registerController", function($scope, userServiceAPI, $state){
	
	$scope.genders = ["MALE", "FEMALE"];

	$scope.message = {};

	$scope.goToLoginPage = function(){
		$state.go("/login");
	};

	$scope.register = function(newUser){
		userServiceAPI.register(newUser).then(function (response) {
			delete $scope.message.registeredError;
			delete $scope.message.userRegistered;
            
            if(response.status === 205){
            	$scope.message.registeredError = "Email sendo utilizado, use outro";
            }else if(response.status === 204){
            	$scope.message.registeredError = "Ocorreu um erro, tente novamente mais tarde";
            }else {
				delete $scope.newUser;
            	$scope.message.userRegistered = "Seu cadastro foi efetuado com sucesso!";
            }
            delete $scope.formRegister;
        
        }), function (response) {
            console.log("Não deu certo");
        };
	};
});