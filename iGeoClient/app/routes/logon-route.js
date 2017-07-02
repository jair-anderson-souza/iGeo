var app = angular.module("app");

app.config(function ($routeProvider) {
    $routeProvider.when("/oferecer", {
        templateUrl: "/oferecer.html",
        controller: "caronaCtrl"
    }).when("/pedir", {
        templateUrl: "/pedir.html"
    }).otherwise({
        redirectTo: "/home.html"
    });
});