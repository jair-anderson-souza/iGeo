/* global google */

var app = angular.module("app");

app.controller("caronaController", function ($scope, apiConfig, rideServiceAPI) {


    $scope.message = {};
    $scope.lat = {};
    $scope.addCityRoute = function () {
        var input = document.createElement("input");
        input.type = "text";
        document.append(input);
        console.log("entour");
    };
    $scope.salvar = function (ride) {
        /*console.log($scope.ride.departureTime);
        $scope.ride.cityInTheMiddle.arrivalTime = $scope.ride.departureTime + $scope.arrivalTime;*/
        rideServiceAPI.saveRide(ride).then(function (response) {
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
    directionsDisplay.setMap($scope.map);
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
            handleLocation(true, infoWindow, $scope.map.getCenter());
        });
    } else {
        handleLocation(false, infoWindow, $scope.map.getCenter());
    }

    function handleLocation(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
            'Error: The Geolocation service failed.' :
            'Error: Your browser doesn\'t support geolocation.');
    }

    function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        infoWindow.setContent("Minha Localização");
        /*
    }*/
    var waypts = [];
    var passage = document.getElementById("passage").value;
    if (passage !== null && passage !== "") {
        waypts.push({
            location: passage,
            stopover: true
        });
    }
    directionsService.route({
        origin: document.getElementById("start").value,
            /*
            */
            optimizeWaypoints: true,
            waypoints: waypts,
            destination: document.getElementById("end").value,
            travelMode: "DRIVING"
        }, function (response, status) {
            if (status === "OK") {
                directionsDisplay.setDirections(response);
                console.log(response.routes[0].legs[0].start_location.lat());
                $scope.ride.cityOrigin.latitude = response.routes[0].legs[0].start_location.lat();
                $scope.ride.cityOrigin.longitude = response.routes[0].legs[0].start_location.lng();
                $scope.ride.distance = response.routes[0].legs[0].distance.value;
                //Tenho que somar a hora de partida com esse aqui
                $scope.ride.arrivalTime = response.routes[0].legs[0].duration.value / 3600;
                
                $scope.ride.cityDestiny.latitude = response.routes[0].legs[0].end_location.lat();
                $scope.ride.cityDestiny.longitude = response.routes[0].legs[0].end_location.lng();
                
                var passage = document.getElementById("passage").value;
                if(passage !== null && passage !== ""){
                    $scope.ride.cityPassage.latitude = response.routes[0].legs[1].start_location.lat();
                    $scope.ride.cityPassage.longitude = response.routes[0].legs[1].start_location.lng();
                }

                
                /*
                $scope.ride.cityInTheMiddle.latitudeDestination = response.routes[0].legs[0].end_location.lat();
                $scope.ride.cityInTheMiddle.longitudeDestination = response.routes[0].legs[0].end_location.lng();
                $scope.ride.cityInTheMiddle.distance = response.routes[0].legs[0].distance.value;
                
                $scope.ride.cityPassage.cityName = document.getElementById("start").value;

                 $scope.ride.routeDestiny = {};
                 $scope.ride.routeDestiny.latitudeOrigin = response.routes[0].legs[1].start_location.lat();
                 $scope.ride.routeDestiny.longitudeOrigin = response.routes[0].legs[1].start_location.lng();
                 $scope.ride.routeDestiny.latitudeDestination = response.routes[0].legs[1].end_location.lat();
                 $scope.ride.routeDestiny.longitudeDestination = response.routes[0].legs[1].end_location.lng();
                 $scope.ride.routeDestiny.cityNameDestination = document.getElementById("end").value;
                 $scope.ride.routeDestiny.cityNameOrigin = document.getElementById("passage").value;
                 
                 */
                /*
                 $scope.ride.cityInTheMiddle.latitudeOrigin = response.routes[1].legs[0].end_location.lat();
                 $scope.ride.routeOrigin.longitudeOrigin = response.routes[1].legs[0].end_location.lng();
                 
                 
                 $scope.ride.routeOrigin.latitudeOrigin = response.routes[1].legs[0].end_location.lat();
                 $scope.ride.routeOrigin.longitudeOrigin = response.routes[1].legs[0].end_location.lng();
                 
                 */               
                 console.log(response.routes[0].legs[1]);
                 console.log("Distance:" + response.routes[0].legs[0].distance.text);
                 console.log("Duration: " + response.routes[0].legs[0].duration.text);
                 console.log("Start Location Lat: " + response.routes[0].legs[0].start_location.lat());
                 console.log("Start Location Lng:" + response.routes[0].legs[0].start_location.lng());
                 console.log("End Location Lat: " + response.routes[0].legs[0].end_location.lat());
                 console.log("End Location Lng:" + response.routes[0].legs[0].end_location.lng());
             } else {
                $scope.formOffer.destination.$invalid = true;
                $scope.formOffer.origin.$invalid = true;
                alert("A cidade informada não foi encontrada");
                /*delete $scope.ride.routeOrigin.citynameOrigin;
                delete $scope.ride.routeDestination.cityNameDestination;*/
            }
        });
}
;
$scope.searchRoute = function () {
    if ($scope.ride.cityOrigin.cityName.toUpperCase() == $scope.ride.cityDestiny.cityName.toUpperCase()) {
        alert("Você informou a mesma cidade!!");
    } else {
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    }
};
});

