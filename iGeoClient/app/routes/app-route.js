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
                cache: false,
                url: "/home",
                views: {
                    "header": {
                        templateUrl: "dashboard2.html"
                    },
                    "content": {
                        templateUrl: "home.html",
                        controller: "loginController"
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
            })
            .state("/oferecer", {
                cache: false,
                url: "/oferecer",
                views: {
                    "header": {
                        templateUrl: "dashboard2.html"
                    },
                    "content": {
                        templateUrl: "oferecer.html",
                        controller: "caronaController"
                    }
                }
            })
            .state("/pedir", {
                cache: false,
                url: "/pedir",
                views: {
                    "header": {
                        templateUrl: "dashboard2.html"
                    },
                    "content": {
                        templateUrl: "pedir.html",
                        controller: "rideCtrl"
                    }
                }
            })
            .state("/minhas", {
                cache: false,
                url: "/minhas",
                views: {
                    "header": {
                        templateUrl: "dashboard2.html"
                    },
                    "content": {
                        templateUrl: "minhas.html",
                        controller: "rideCtrl"
                    }
                }
            })
            .state("/exit", {
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

                }});
});

