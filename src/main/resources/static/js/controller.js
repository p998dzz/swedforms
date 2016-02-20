var appControllers = angular.module('controllers', []);

appControllers.controller('loginController', function($scope, $http, $rootScope, $window) {
    $rootScope.url = "http://localhost:8080";

    $scope.register = function() {
           $window.location.href = '/#/newUser'; //jei pakeisim psl tai i overview
        }

    $scope.login = function() {
       var email = $( "#emailField" ).val();
       var password = $( "#passwordField" ).val();
       $http({
           method: 'POST',
           url: $rootScope.url+'/authenticate',
           data: { "email": email, "pass": password }
       }).then(function successCallback(response) {
                     if(response.data != "")
                     {
                        $rootScope.user = response.data;
                        $window.location.href = '/#/home';
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
        $window.location.href = '/#/overview';
     }
    $scope.cont = function() {
        $window.location.href = '/#/ContactUs';
  }
});

appControllers.controller('newRegistrationController', function($scope, $http, $rootScope, $window) {
    $http({
       method: 'POST',
       url: $rootScope.url+'/getDataForRegistration',
       data: {"user" : $rootScope.user}
   }).then(function successCallback(response) { //nezinau dar kaip ta dali nuo then keist reik
                 if(response.data != "")
                 {
                    $scope.dates = response.data;
                 }else
                 {
                    alert("Įvyko klaida");
                 }
               }, function errorCallback(response) {
                    alert("Problemos su interneto ryšiu");
               });


    $scope.register = function() {
           var name = $( "#nameField" ).val();
           var surname = $( "#surnameField" ).val();
           var phone = $( "#phoneField" ).val();
           var email = $( "#emailField" ).val();
           var unit = $("#unitSelect").val();
           var date = $("#dateField").val();
          // var time = $("#timeSelect").val();
           var topic = $("#topicSelect").val();
           var comment = $("#commentField").val();

            $rootScope.regData = { "name":name, "surname": surname, "phone":phone, "email": email, "unit":unit, "date": date,
            "topic": topic, "comment": comment, "user": rootScope.user };
           $http({
               method: 'POST',
              // url: $rootScope.url+'/checkRegistration',  sitoj vietoj nelabai suprantu koki uml rasyt
               data: $rootScope.regData
           }).then(function successCallback(response) { //nezinau dar kaip ta dali nuo then keist reik
                         if(response.data == "OK")
                         {
                            $window.location.href = '/#/home';  //siaip turetu eit i registration confirmation
                         }else
                         {
                            alert("Įvyko klaida");
                         }
                       }, function errorCallback(response) {
                            alert("Problemos su interneto ryšiu");
                       });
    }
    $scope.cancel = function() {
           $window.location.href = '/#/home'; //jei pakeisim psl tai i overview
    }
});

appControllers.controller('ContactUsController', function($scope, $http, $rootScope, $window) {


       $scope.contact = function() {
               var topic = $("#topicSelect").val();
               var message = $("#messageField").val();
               var name = $( "#nameField" ).val();
               var lastname = $( "#lastnameField" ).val();
               var phone = $( "#phoneField" ).val();
               var email = $( "#emailField" ).val();

               $http({
                   method: 'POST',
                 //  url: $rootScope.url+'/createContact',  sitoj vietoj nelabai suprantu koki uml rasyt
                   data: { "topic":topic, "message":message, "name":name, "lastname": lastname, "phone":phone, "email": email, "user": rootScope.user}
               }).then(function successCallback(response) { //nezinau dar kaip ta dali nuo then keist reik
                             if(response.data != "OK")
                             {
                                $window.location.href = '/#/confirmation';
                             }else
                             {
                                alert("Įvyko klaida");
                             }
                           }, function errorCallback(response) {
                                alert("Problemos su interneto ryšiu");
                           });

               }

       $scope.cancel = function() {
               $window.location.href = '/#/home';
               }
});

appControllers.controller('confirmationController', function($scope, $http, $rootScope, $window) {
    $scope.conf = function() {
        $window.location.href = '/#/overview';
  }
});

appControllers.controller('newUserController', function($scope, $http, $rootScope, $window) {

    $scope.ureg = function() {
           var email = $( "#emailField" ).val();
           var password = $( "#passwordField" ).val();
           $http({
               method: 'POST',
               url: $rootScope.url+'/createUser',
               data: { "email": email, "pass": password}
           }).then(function successCallback(response) {
                         if(response.data == "")
                         {
                            $window.location.href = '/#/';
                         }else
                         {
                            $rootScope.user = response.data;
                            $window.location.href = '/#/home';
                         }
                       }, function errorCallback(response) {
                            alert("Problemos su interneto ryšiu");
                       });
        };

$scope.cancel = function() {
            $window.location.href = '/#/';
            };
});

appControllers.controller('overviewController', function($scope, $http, $rootScope, $window) {

$scope.regi = function() {
        $window.location.href = '/#/newRegistration';
     }
});