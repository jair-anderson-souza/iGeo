var app = angular.module("app2", ["ui.router"]);

app.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider.state("/home", {
        cache: false,
        url: "/home",
        views: {
            "header": {
                templateUrl: "dashboard2.html"
            },
            "content": {
                templateUrl: "home.html",
                controller: "caronaController"
            }
        }
    });

});

