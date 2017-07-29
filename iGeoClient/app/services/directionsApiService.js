var app = angular.module("app");

app.service("directionService", function($http){
	
	var _getDiretion = function(start, end){
		return $http.get("https://maps.googleapis.com/maps/api/directions/json?origin=" + start + "&destination=" + end + "&key=AIzaSyBTdf2tYuR20XgsTUSU7h8VbG3ksNoUuNE")
	}

	return {
		getDiretion : _getDiretion
	}
});