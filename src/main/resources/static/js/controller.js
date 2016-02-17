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

appControllers.controller('homeController', function($scope, $http, $rootScope, $window) {
    $scope.regi = function() {
        $window.location.href = '/#/newRegistration';
     }
    $scope.cont = function() {
        $window.location.href = '/#/ContactUs';
  }
});

appControllers.controller('newRegistrationController', function($scope, $http, $rootScope, $window) {
    $scope.register = function() {
           var name = $( "#nameField" ).val();
           var surname = $( "#surnameField" ).val();
           var telNumber = $( "#telNumberField" ).val();
           var email = $( "#emailField" ).val();

           $http({
               method: 'POST',
             //  url: $rootScope.url+'/authenticate',  sitoj vietoj nelabai suprantu koki uml rasyt
               data: { "name":name, "surname": surname, "telNumber":telNumber, "email": email}
           }).then(function successCallback(response) {
                         if(response.data != "null")
                         {
                            $rootScope.user = response.data;
                            $window.location.href = '/home';  //siaip turetu eit i registration confirmation ir nezinau dar kaip ta dali nuo then keist reik
                         }else
                         {
                            alert("Neteisingas vartotojas arba slaptažodis");
                         }
                       }, function errorCallback(response) {
                            alert("Problemos su interneto ryšiu");
                       });

            //$window.location.href = '/home';
         }
});

appControllers.controller('ContactUsController', function($scope, $http, $rootScope, $window) {

});

appControllers.controller('confirmationController', function($scope, $http, $rootScope, $window) {
    $scope.conf = function() {
        $window.location.href = '/#/home';
  }
});
