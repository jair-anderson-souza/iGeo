var app = angular.module("app");

app.controller("loginController", function ($scope, userServiceAPI, $http, $location, $localStorage) {
    $scope.login = function (loginVO) {
        userServiceAPI.login(loginVO).then(function (response) {
        	if(response.data){
        		console.log(response.config.headers);
            	$location.path("src/views/home.html");
            	$localStorage.currentUser = {token : response.data};
            	/*$http.defaults.headers.commom.Authorization = response.data;*/
            }else{
            	
            }
        }), function (response) {

        	callback(false);
            console.log("Não deu certo");
        };
    };
});