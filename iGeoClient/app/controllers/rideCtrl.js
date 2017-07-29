var app = angular.module("app");

app.controller("rideCtrl", function ($scope, rideServiceAPI, $http) {
    console.log("entrou");
    $http.get("http://localhost:8080/iGeoWebServices/webresources/ride").then(function (response) {
        if (response.status == 200) {
            console.log(response);
            $scope.rides = response.data;
        }
    }), function (response) {
    };

});