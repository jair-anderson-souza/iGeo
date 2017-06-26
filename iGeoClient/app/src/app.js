var app = angular.module("app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider.when("/login", {
        templateUrl: "../login.html",
        controller: "loginController"
    }).when("/register", {
        templateUrl: "../register.html",
        controller: "registerController"
    });
});