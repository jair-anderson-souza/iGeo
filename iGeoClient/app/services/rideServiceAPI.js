var app = angular.module("app");

app.service("rideServiceAPI", function($http, apiConfig){
	var _saveRide = function(ride, id){
		return $http.post(apiConfig.api + "/ride/" + id, ride);
	};

	var _getAllRides = function(){
		return $http.get(apiConfig.api + "/ride");
	}

	var _getRides = function(origin, date){
		return $http({
			method : "GET",
			url : apiConfig.api + "/ride/list",
			params : {"origin": origin, "date" : date}
		});	
	}

	var _searchMyRides = function(id){
		return $http.get(apiConfig.api + "/ride/" + id);	
	}
	
	return {
		saveRide : _saveRide,
		getAllRides : _getAllRides,
		getRides : _getRides
		searchMyRides : _searchMyRides
	}
});