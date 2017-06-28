var app = angular.module("app");

app.service("userServiceAPI", function($http, apiConfig){
	var _login = function(loginVO){
		return $http.post(apiConfig.api + "/login", loginVO);
	};

	var _register = function(newUser){
		console.log(newUser);
		return $http.post(apiConfig.api + "/register", newUser);
	};
	return {
		login : _login,
		register : _register
	};
});