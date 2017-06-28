var app = angular.module("app", ["ngRoute"]);

app.config(function ($routeProvider, $locationProvider) {

    $routeProvider.when("/login", {
        templateUrl: "login.html",
        controller: "loginController"
    }).when("/register", {
        templateUrl: "register.html",
        controller: "registerController"
    });
	$locationProvider.html5Mode(true);
});