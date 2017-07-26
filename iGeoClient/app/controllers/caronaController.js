var app = angular.module("app");

app.controller("caronaController", function ($scope, $http, apiConfig, rideServiceAPI) {
    loadRides();

    $scope.message = {};

    function loadRides() {
        $http.get(apiConfig.api + "/ride").then(function (response) {
            if (response.status == 200) {
                console.log(response);
                $scope.rides = response.data;
            }
        }), function (response) {
        };
    }


    $scope.addCityRoute = function () {
        var input = document.createElement("input");
        input.type = "text";
        document.append(input);
        console.log("entour");
    }



    $scope.salvar = function (ride) {

        $http.post(apiConfig.api + "/ride/1", ride).then(function (response) {
            if (response.status === 200) {
                delete $scope.ride;
                delete $scope.formOffer;
                $scope.message.rideSuccessful = "A carona foi registrada com sucesso";
            } else if (response.status === 204) {
                $scope.message.rideError = "Ocorreu um erro, tente novamente";
            }
            delete $scope.formOffer;
            console.log("Entour");
        }), function (response) {
            console.log("Ñ Entour");
        };
    };


    var directionsService = new google.maps.DirectionsService();
    var directionsDisplay = new google.maps.DirectionsRenderer();

    $scope.map = new google.maps.Map(document.getElementById("map"), {
        zoom: 8,
        center: {lat: 41.85, lng: -87.65}
    });



    /*google.maps.event.addDomListener(window, "load", onLoad($scope));*/

    directionsDisplay.setMap($scope.map);

    var onChangedHandler = function () {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    };

    var infoWindow = new google.maps.InfoWindow({map: $scope.map});
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            infoWindow.setPosition(pos);
            infoWindow.setContent("Localização encontrada");
            $scope.map.setCenter(pos);
        }, function () {
            handleLocation(true, infoWindow, map.getCenter());
        });
    } else {
        handleLocation(false, infoWindow, map.getCenter());
    }

    function handleLocation(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                'Error: The Geolocation service failed.' :
                'Error: Your browser doesn\'t support geolocation.');
    }

    function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        var waypts = [];
        infoWindow.setContent("Minha Localização");
        var passage = document.getElementById("passage").value;
        if (passage !== null && passage !== "") {
            waypts.push({
                location: passage,
                stopover: true
            });
        }
        directionsService.route({
            origin: document.getElementById("start").value,
            optimizeWaypoints: true,
            waypoints: waypts,
            destination: document.getElementById("end").value,
            travelMode: "DRIVING"
        }, function (response, status) {
            if (status === "OK") {
                directionsDisplay.setDirections(response);
            } else {
                $scope.formOffer.destination.$invalid = true;
                $scope.formOffer.origin.$invalid = true;
                alert("A cidade informada não foi encontrada");
                delete $scope.ride.cityOrigin;
                delete $scope.ride.cityDestiny;
            }
        });
    }

    $scope.searchRoute = function () {
        if ($scope.ride.cityOrigin.toUpperCase() == $scope.ride.cityDestiny.toUpperCase()) {
            alert("Você informou a mesma cidade!!");
        } else {
            calculateAndDisplayRoute(directionsService, directionsDisplay);
        }
    };
});
        