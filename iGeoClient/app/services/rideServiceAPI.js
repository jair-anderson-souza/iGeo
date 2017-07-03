var app = angular.module("app");

app.service("rideServiceAPI", function($http, apiConfig){
	var _saveRide = function(ride, id){
		console.log(id);
		return $http.post(apiConfig.api + "/ride/" + id , ride);
	};
	return {
		saveRide : _saveRide
	}
});