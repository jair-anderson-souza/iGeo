var app = angular.module("app", []);
app.controller("initController", function($scope){
	$scope.login = function(loginVO){
		console.log(loginVO);
	};
});