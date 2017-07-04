var app = angular.module("app");

app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/");
    $stateProvider
            .state("/", {
                cache: false,
                url: "/",
                views: {
                    "header": {
                        templateUrl: "dashboard.html"
                    }
                }
            })
            .state("/login", {
                cache: false,
                url: "/login",
                views: {
                    "header": {
                        templateUrl: "dashboard.html"
                    },
                    "content": {
                        templateUrl: "login.html",
                        controller: "loginController"
                    }
                }
            })
            .state("/home", {
                cache : false,
                url : "/home",
                views : {
                    "header" : {
                        templateUrl : "dashboard2.html",
                        controller : "caronaController"
                    },
                    "content" : {
                        templateUrl : "home.html",
                        controller : "caronaController"
                    }
                }
            })
            .state("/register", {
                cache: false,
                url: "/register",
                views: {
                    "header": {
                        templateUrl: "dashboard.html"
                    },
                    "content": {
                        templateUrl: "register.html",
                        controller: "registerController"
                    }
                }
            });
});

