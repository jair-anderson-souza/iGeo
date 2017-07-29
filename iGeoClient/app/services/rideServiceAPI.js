var app = angular.module("app");

app.service("rideServiceAPI", function($http, apiConfig){
	var _saveRide = function(ride, id){
		return $http.post(apiConfig.api + "/ride/" + id , ride);
	};

	var _getRides = function(){
		return $http.get(apiConfig.api + "/ride");
	}
	
	return {
		saveRide : _saveRide,
		getRide : _getRides
	}
});