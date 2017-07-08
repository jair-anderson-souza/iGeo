var app = angular.module("app");
app.controller("caronaController", function ($scope, $http, apiConfig) {

    $scope.salvar = function (ride) {
        
        $http.post(apiConfig.api + "/ride", ride).then(function (response) {
            console.log("Entour");
        }), function (response) {
            console.log("Ñ Entour");
        };
    };


    var directionsService = new google.maps.DirectionsService();
    var directionsDisplay = new google.maps.DirectionsRenderer();

    $scope.map = new google.maps.Map(document.getElementById("map"), {
        zoom: 8,
        center: {lat: 0, lng: 0}
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
        directionsService.route({
            origin: document.getElementById("start").value,
            destination: document.getElementById("end").value,
            travelMode: "DRIVING"
        }, function (response, status) {
            if (status === "OK") {
                directionsDisplay.setDirections(response);
            } else {
                alert("A cidade informada não foi encontrada");
            }
        });
    }
    $scope.searchRoute = function () {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    };
});
        