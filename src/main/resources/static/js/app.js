var swedApp =  angular.module('UserForms', [
  'controllers',
  'ngRoute'
]);

swedApp.config(['$routeProvider',
  function($routeProvider, $rootScope) {
    $routeProvider.
      when('/', {
        templateUrl: 'partials/login.html',
        controller: 'loginController'
      }).
      when('/home', {
        templateUrl: 'partials/home.html',
        controller: 'homeController'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);