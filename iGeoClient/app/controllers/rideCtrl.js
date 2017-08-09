var app = angular.module("app");

app.controller("rideCtrl", function ($scope, $http, apiConfig, $rootScope, $state) {

	$scope.ridesBy = {};
	
	$scope.search = function (origin, date) {
		 $http({
			method : "GET",
			url : apiConfig.api + "/ride/list",
			params : {"origin": origin, "date" : date}
		}).then(function (response) {
			$scope.ridesBy = response.data;		
		}), function (response) {
			console.log(response);
		};
	};

	var id;
	var token = $rootScope.token;
	var token
	if(token == null || token == ""){
		$state.go("/login");
	}else{
		id = $rootScope.token.id;
	}

	$http.get(apiConfig.api + "/ride/" + id).then(function(response){
		console.log("Entrou");
		$scope.myRides = response.data;
	}), function(response){
		console.log(response);
	};

});