var app = angular.module("app");

app.config(function ($routeProvider) {
    $routeProvider.when("/oferecer", {
        templateUrl: "../views/oferecer.html",
        controller: "caronaCtrl"
    }).when("/pedir", {
        templateUrl: "../views/pedir.html"
    }).otherwise({
        redirectTo: "../index.html"
    });
});