var app = angular.module("app", ["ui.router"]);

app.controller("ctrl", function($scope, $state){
	$scope.ir = function(){
		$state.go("login");
	}
});
