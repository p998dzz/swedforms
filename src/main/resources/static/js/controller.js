var appControllers = angular.module('controllers', []);

appControllers.controller('loginController', function($scope, $http, $rootScope, $window) {
    $rootScope.url = "http://localhost:8080";
    $scope.login = function() {
       var email = $( "#emailField" ).val();
       var password = $( "#passwordField" ).val();
       $http({
           method: 'POST',
           url: $rootScope.url+'/authenticate',
           data: { "email": email, "pass": password }
       }).then(function successCallback(response) {
             if(response.data != "null")
             {
                $rootScope.user = response.data;
                $window.location.href = '/home';
             }else
             {
                alert("Neteisingas vartotojas arba slaptažodis");
             }
           }, function errorCallback(response) {
                alert("Problemos su interneto ryšiu");
           });
     }
});
