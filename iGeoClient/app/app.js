var app = angular.module("app", ["ngRoute", "ngStorage"]);

app.config(function ($routeProvider, $locationProvider) {

    $routeProvider.when("/login", {
        templateUrl: "login.html",
        controller: "loginController"
    }).when("/register", {
        templateUrl: "register.html",
        controller: "registerController"
    }).when("/home", {
    	template : "src/views/home.html"
    });
	$locationProvider.html5Mode(true);
});

app.run(function($rootScope, $http, $location, $localStorage){
	/*
	if ($localStorage.currentUser) {
            $http.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        };

        // redirect to login page if not logged in and trying to access a restricted page
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            var publicPages = ['/login'];
            var restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !$localStorage.currentUser) {
                $location.path('/login');
            }
        });*/
});