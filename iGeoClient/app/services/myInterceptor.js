/*
var app = angular.module("app");

app.factory('testInterceptor', testInterceptor).config(function ($httpProvider) {
    $httpProvider.interceptors.push('testInterceptor');
});

function testInterceptor($location) {
  var token = {};
  return {
    request: function(config) {
        config.headers = config.headers || {};
        if(config.headers.SigningKey){
            $location.path("register.html");
        };

        console.log("Request: " + config.headers.SigningKey);
        return config;
    },

    requestError: function(config) {
      console.log("RequestError: " + config.headers.SigningKey);
      return config;
    },

    response: function(res) {
    console.log("Response:" + res.data);
      if(res.status == 200){
        $location.path("register.html");
      }
      return res;
    },

    responseError: function(res) {
    console.log("ResponseError:" + res.data);
      var token = res.headers["SigningKey"];
      if(token == 200){
        $location.path("index.html");
      }
      return res;
    }
  }
};
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
.run(function($http) {
  $http.get('http://test-routes.herokuapp.com/test/hello')
    .then(function(res) {
      console.log(res.data.message)
    })
})
*/