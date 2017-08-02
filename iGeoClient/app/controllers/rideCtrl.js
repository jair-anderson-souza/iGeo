var app = angular.module("app");

app.controller("rideCtrl", function ($scope, rideServiceAPI) {

    $scope.search = function (origin, destination, date) {
        rideServiceAPI.getRides(origin, destination, date).then(function (response) {
            console.log(response);
            $scope.ridesBy = response.data;
        }), function (response) {
            console.log("Erro");
        };
    };

});