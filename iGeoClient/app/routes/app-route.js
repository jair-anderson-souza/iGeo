var app = angular.module("app");

app.config(function ($routeProvider) {
    $routeProvider.when("/dashboard", {
        templateUrl: "dashboard.html"
    }).when("/login", {
        templateUrl: "login.html",
        controller: "loginController"
    }).when("/register", {
        templateUrl: "/register.html",
        controller: "registerController"
    });
});
