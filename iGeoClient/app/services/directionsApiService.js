var app = angular.module("app");

app.service("directionService", function($http){
	
	var _getDirection = function(start, end){
		return $http({
			method : "GET",
			url : "https://maps.googleapis.com/maps/api/directions/json",
			params : {
				"origin" : start,
				"destination" : end,
				"key" : "AIzaSyBTdf2tYuR20XgsTUSU7h8VbG3ksNoUuNE"
			}
		});

		//return $http.get("https://maps.googleapis.com/maps/api/directions/json?origin=Cajazeiras&destination=Apodi&key=AIzaSyBTdf2tYuR20XgsTUSU7h8VbG3ksNoUuNE");
	}

	return {
		getDirection : _getDirection
	}
});