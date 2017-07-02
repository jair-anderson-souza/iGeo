var app = angular.module("app");
app.controller("caronaController", function ($scope) {
    
//dados da inicialização do mapa
    var options = function () {
        return  pos = {
            zoom: 15,
            center: {lat: -25.363, lng: 131.044}
        };
    };
    $scope.map = new google.maps.Map(document.getElementById("map"), options.getPos());
    var marker = {};
    var marker2 = {};
    var directionService = new google.maps.DirectionsService;
    var directionDisplay = new google.maps.DirectionsRenderer;
    //Pegando a geolocalização
    var infoWindow = new google.maps.InfoWindow({map: $scope.map});
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            infoWindow.setPosition(pos);
            $scope.map.setCenter(pos);
            marker = new google.maps.Marker({
                position: pos,
                animation: google.maps.Animation.DROP,
                draggable: true,
                title: "Local de Saída",
                map: $scope.map
            });
        });
    }
    //                };
    //                };
    directionDisplay.setMap($scope.map);
    var call = function () {
        directionService.route({
            origin: $scope.start,
            destination: document.getElementById("end").value,
            travelMode: "DRIVING"
        }, function (response, status) {
            if (status === "OK") {
                directionDisplay.setDirections(response);
                console.log(directionsDisplay);
            } else {
                alert("Ocorreu um erro");
            }
        });
    };
    document.getElementById("start").addEventListener("change", call);
    document.getElementById("end").addEventListener("change", call);
});