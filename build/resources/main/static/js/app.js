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
      when('/newRegistration', {
              templateUrl: 'partials/newRegistration.html',
              controller: 'newRegistrationController'
            }).
      when('/ContactUs', {
              templateUrl: 'partials/ContactUs.html',
              controller: 'ContactUsController'
            }).
      otherwise({
        redirectTo: '/'
      });
  }]);