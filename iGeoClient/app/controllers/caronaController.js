var app = angular.module("app");

app.controller("caronaController", function ($scope, rideServiceAPI, $http) {

    $scope.salvar = function (ride) {
        $http.post(apiConfig.api + "/ride/" + id, ride).then(function (response) {
            console.log("Entrou");
        }), function (response) {
            console.log("Não Entrou");
        };
    };
    var initialize = function() {
        var mapOptions = {
            center: new google.maps.LatLng(41.894465, -88.460500),
            zoom: 12,
            mapTypeId: google.maps.MapTypeId.HYBRID
        };
        var map = new google.maps.Map(document.getElementById("map"), mapOptions);
    };

    google.maps.event.addListenerOnce(window, "load", initialize);

});


   //rideServiceAPI.saveRide(ride, 1).then(function (response) {
/*
    $scope.addDiv = function(){
        $scope.element = document.createElement("div");
        $scope.element.id = "map";
        document.body.append($scope.element);
        $scope.addResource();
    }
 
    $scope.addResource = function(){
        $scope.element = document.createElement("script");
        $scope.element.type = "https://maps.googleapis.com/maps/api/js?key=AIzaSyCmmEch_9VmTUy7RMikSa0mGk9mqZGwh2M&amp;sensor=false";
        element.src = "text/javascript";
        document.body.append($scope.element);
        $scope.initialize();
    }
 var map = new google.maps.Map(document.getElementById("map"), {
 zoom: 15,
 center: {lat: 41.85, lng: -87.65};
 });
 
 
 //    
 //     
 //     var div = function(){
 //     var element = document.createElement("div");
 //     element.id = "map";
 //     document.body.append(element);
 //     };
 /*
 
 var map = new google.maps.Map(document.getElementById("id"), initialize);
 
 document.getElementById("panel").addEventListener("ready", function(){
 var element = document.createElement("div");
 element.id = "map";
 document.body.append(element);
 });
 
 
 
 
 //dados da inicialização do mapa
 var initalizeMap = {
 zoom: 15,
 center: {lat: 41.85, lng: -87.65}
 };
 $scope.map = new google.maps.Map(document.getElementById("map2"), initalizeMap);
 var marker = {};
 var infoWindow = new google.maps.InfoWindow({map: $scope.map});
 if (navigator.geolocation) {
 navigator.geolocation.getCurrentPosition(function (position) {
 var pos = {
 lat: position.coords.latitude,
 lng: position.coords.longitude
 };
 infoWindow.setPosition(pos);
 $scope.map.setCenter(pos);
 createMarker(pos);
 });
 }
 
 google.maps.event.add
 
 function createMarker(pos) {
 marker = new google.maps.Marker({
 position: pos,
 animation: google.maps.Animation.DROP,
 draggable: true,
 map: $scope.map
 });
 console.log(marker);
 $scope.ride.latitudeOrigin = pos.lat;
 $scope.ride.longitudeOrigin = pos.lng;
 }
 ;
 var directionService = new google.maps.DirectionsService;
 var directionDisplay = new google.maps.DirectionsRenderer;
 directionDisplay.setMap($scope.map);
 var createDirection = function () {
 directionService.route({
 origin: document.getElementById("start").value,
 destination: document.getElementById("end").value,
 travelMode: "DRIVING",
 }, function (response, status) {
 if (status === "OK") {
 console.log(response);
 directionDisplay.setDirections(response);
 console.log(directionsDisplay);
 } else {
 map.setCenter(initalizeMap);
 }
 }
 );
 };
 document.getElementById("start").addEventListener("change", createDirection);
 document.getElementById("end").addEventListener("change", createDirection);
 $scope.userprincipal = {};
 $scope.userprincipal.rides = $scope.ride;
 $scope.salvar = function () {
 console.log($scope.ride.origin);
 console.log($scope.ride.destiny);
 $http.put("http://localhost:8080/iGeoWebServices/webresources/ride", $scope.userprincipal).
 then(function (response) {
 delete $scope.ride;
 console.log("Deu certo");
 }), function (response) {
 console.log("Não deu certo");
 };
 };
 // */
//             var marker2 = {};
//             
//             //Pegando a geolocalização
//             */
//            };
//});
//app.controller("caronaControllerasdasd", function ($scope) {
//
////dados da inicialização do mapa
//    var options = function () {
//        return  pos = {
//            zoom: 15,
//            center: {lat: -25.363, lng: 131.044}
//        };
//    };
//    $scope.map = new google.maps.Map(document.getElementById("map"), options.getPos());
//    var marker = {};
//    var marker2 = {};
//    var directionService = new google.maps.DirectionsService;
//    var directionDisplay = new google.maps.DirectionsRenderer;
//    //Pegando a geolocalização
//    var infoWindow = new google.maps.InfoWindow({map: $scope.map});
//    if (navigator.geolocation) {
//        navigator.geolocation.getCurrentPosition(function (position) {
//            var pos = {
//                lat: position.coords.latitude,
//                lng: position.coords.longitude
//            };
//            infoWindow.setPosition(pos);
//            $scope.map.setCenter(pos);
//            marker = new google.maps.Marker({
//                position: pos,
//                animation: google.maps.Animation.DROP,
//                draggable: true,
//                title: "Local de Saída",
//                map: $scope.map
//            });
//        });
//    }
//    //                };
//    //                };
//    directionDisplay.setMap($scope.map);
//    var call = function () {
//        directionService.route({
//            origin: $scope.start,
//            destination: document.getElementById("end").value,
//            travelMode: "DRIVING"
//        }, function (response, status) {
//            if (status === "OK") {
//                directionDisplay.setDirections(response);
//                console.log(directionsDisplay);
//            } else {
//                alert("Ocorreu um erro");
//            }
//        });
//    };
//    document.getElementById("start").addEventListener("change", call);
//    document.getElementById("end").addEventListener("change", call);
//});