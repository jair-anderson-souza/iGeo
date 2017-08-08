var app = angular.module("app");

app.controller("rideCtrl", function ($scope, rideServiceAPI) {

	$scope.ridesBy = {};
	
	$scope.search = function (origin, date) {
		rideServiceAPI.getRides(origin, date).then(function (response) {
			$scope.ridesBy = response.data;		
		}), function (response) {
			console.log(response);
		};
	};

});