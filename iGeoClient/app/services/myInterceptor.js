var app = angular.module("app");

app.factory('testInterceptor', testInterceptor).config(function ($httpProvider) {
  $httpProvider.interceptors.push('testInterceptor');
});

function testInterceptor($location, $state, $q, $timeout, $rootScope) {
  var token = {};
  return {
    request: function(config) {
      console.log($rootScope.token);
      var token = $rootScope.token;
      if(token !== null && typeof token != 'undefined'){
        $rootScope.token = token;
      }else{
        $state.go("/login");
      }
      return config;
    },

    requestError: function(config) {
      return config;
    },

    response: function(res) {
      var token = res.data;
      if(token !== null && typeof token != 'undefined'){
        $rootScope.token = token;
      }else{
        $state.go("/login");
      }
      return res;
    },

    responseError: function(res) {
      $state.go("/");
      return res;
    }

  }
};
