var app = angular.module("app");

app.factory('testInterceptor', testInterceptor).config(function ($httpProvider) {
    $httpProvider.interceptors.push('testInterceptor');
});
function testInterceptor($rootScope, $location, $q, $timeout, $rootScope) {
  var token = {};
  return {
    request: function(config) {
        config.headers = config.headers || {};
        console.log($rootScope);

        return config;
    },

    requestError: function(config) {
      console.log($rootScope);
      /*if(res.status !== 200){
        $location.path("/index");
      };
      return $q.reject(config);
      
    },
  */  return config;
},
    response: function(res) {
      console.log($rootScope);
      /*
      if(res.status == 200 && !res.data){
        
      };
      if(res.data == $rootScope.token){
        $location.path("/");
      }
      if(res.data){
        $rootScope.token = res.data;
        
      }
      console.log("RootScope:" + res.data);
      if(res.status == 404 && !$rootScope.token){
        delete $rootScope.token;
        $location.path("/");
      return res;
      */
      return res;
    },

    responseError: function(res) {
      console.log($rootScope);
      /*
      console.log("ResponseError:" + res.status);
      console.log("ResponseError:" + res.headers);
      console.log("ResponseError:" + res.statusText);
      console.log("ResponseError:" + res.data);*/
      return res;
    }
  }
};
/*
app.factory('AuthService', AuthService);

function AuthService ($http, $q) {
  return {
    getToken : function () {
      return $localStorage.token;
    },
    setToken: function (token) {
      $localStorage.token = token;
    },
    signin : function (data) {
      $http.post('api/signin', data);
    },
    signup : function (data) {
      $http.post('api/signup', data);
    },
    logout : function (data) {
      delete $localStorage.token;
      $q.when();
    }
  };
}
/*
app.run(function($http, $location, $rootScope) {
    $rootScope.$on('loginRequired', function() {
      $location.path('/login');
  });
});
*/