var appControllers = angular.module('controllers', []);

appControllers.controller('loginController', function($scope, $http, $rootScope, $window) {
    $rootScope.url = "http://localhost:8080";

    $scope.register = function() {
           $window.location.href = '/#/newUser';
        }

    $scope.login = function() {
       var email = $( "#emailField" ).val();
       var password = $( "#passwordField" ).val();
       $http({
           method: 'POST',
           url: $rootScope.url+'/authenticate',
           data: { "email": email, "pass": password }
       }).then(function successCallback(response) {
                     if(response.data == "OK")
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

//    jQuery(function(){
//    var enableDates = ["2016-02-20"]; //= kas apacioj
//     function enableAllTheseDays(date) {
//     var sdate = $.datepicker.formatDate( 'yy-mm-dd', date)
//     console.log(sdate)
//    if($.inArray(sdate,enableDates) != -1) { //vietoj enable butu posible days
//    return [true];
//                   }
//    return [false];
//    }
//    jQuery('#dateField').datepicker({dateFormat: 'yy-mm-dd', beforeShowDay: enableAllTheseDays});
//    })

    $http({
         method: 'GET',
         //url: $rootScope.url+'/getDataForRegistration'
         }).then(function successCallback(response) {
              if(response.data != "")
              {
                  jQuery(function(){
                    $scope.data = response.data;
                    $scope.possibleDates = new Array();
                    for(var i = 0; i < $scope.possibleDates.length; i++)
                    {
                    possibleDates.push($scope.possibleDates[i].date);
                    }

                    function enableAllTheseDays(date) {
                    var sdate = $.datepicker.formatDate( 'yy-mm-dd', date)
                    console.log(sdate)
                    if($.inArray(sdate,possibleDates) != -1) {
                            return [true];
                    }
                            return [false];
                    }
                    jQuery('#dateField').datepicker({dateFormat: 'yy-mm-dd', beforeShowDay: enableAllTheseDays});
                    })

              }
              else
              {
                 alert("Negautos datos iš serverio");
              }
              }, function errorCallback(response) {
                 alert("Problemos su interneto ryšiu");
              });

              /* $scope.dayOnClick = function(){
                       var date = $("#dateField").val();
                       var timee = $("timeField").val()
                       var enableTime = ["08:00","09:00"]; //= kas apacioj

                       for(index in enableTime) {
                           timee.options[timee.options.length] = new Option(enableTime[index], index);
                       }*/
                       //$scope.possibleTimes = new Array();


              //             for(var i = 0; i < $scope.data; i++)
              //              {
              //                     if(date == $scope.data[i].date)
              //                     {
              //                         var times = $scope.data[i].time;
              //                         for(var j = 0; j<times.length; j++)
              //                           $("timeField").push(times[j]);
              //                         break;
              //                     }
              //              }

    $scope.register = function() {
           var name = $( "#nameField" ).val();
           var surname = $( "#surnameField" ).val();
           var phone = $( "#phoneField" ).val();
           var email = $( "#emailField" ).val();
           var unit =  $("#unitSelect").val();
           var date = $("#dateField").val();
          // var time = $("#timeSelect").val();
           var topic = $("#topicSelect").val();
           var comment = $("#commentField").val();

            $rootScope.regData = { "name":name, "surname": surname, "phone":phone, "email": email, "unit":unit, "date": date,
            "topic": topic, "comment": comment, "user": rootScope.user }
           $http({
               method: 'POST',
              // url: $rootScope.url+'/checkRegistration',
               data: $rootScope.regData
           }).then(function successCallback(response) {
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
           $window.location.href = '/#/overview';
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
               var radio1 = $("responcephone").val();
               var radio2 = $("responceemail").val();
               var radio3 = $("responceboth").val();
               var radio;

               if(radio1 != null)
                radio = radio1;
               else if(radio2 != null)
                radio = radio2;
               else
                radio = radio3;

               $http({
                   method: 'POST',
                 //  url: $rootScope.url+'/createContact',
                   data: { "topic":topic, "message":message, "name":name, "lastname": lastname,
                   "phone":phone, "email": email, "radio":radio, "user": rootScope.user}
                                  }).then(function successCallback(response) {
                             if(response.data == "OK")
                             {
                                $window.location.href = '/#/confirmation';
                             }else
                             {
                                alert("Įvyko klaida");
                             }
                           }, function errorCallback(response) {
                                alert("Problemos su interneto ryšiu");
                           });

               };

       $scope.cancel = function() {
               $window.location.href = '/#/home';
               }
});

appControllers.controller('confirmationController', function($scope, $http, $rootScope, $window) {
    $scope.conf = function() {
        $window.location.href = '/#/home';
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
                         if(response.data == "OK")
                         {

                            $window.location.href = '/#/';
                         }else
                         {
                            alert("Jau egzisyuoja toks vartotojas");

                            $rootScope.user = response.data;
                        //    $window.location.href = '/#/home'; kodel domantai cia padarei kad siustu i home?

                         }
                       }, function errorCallback(response) {
                            alert("Problemos su interneto ryšiu");
                       });
        }

$scope.cancel = function() {
            $window.location.href = '/#/';
            }
});

appControllers.controller('overviewController', function($scope, $http, $rootScope, $window) {
$http({
         method: 'GET',
         url: $rootScope.url+'/getRegistrations'
         }).then(function successCallback(response) {
              if(response.data != "")
              {
               $scope.products = response.data;
              }
              else
              {
                 alert("Registracijų neturite");
              }
              }, function errorCallback(response) {
                 alert("Problemos su interneto ryšiu");
              });

/*$scope.products = [
       { id: 1, name: 'test', color: 'lightblue' },
       { id: 2, name: 'bob', color: 'blue' }]*/

$scope.regi = function() {
        $window.location.href = '/#/newRegistration';
     }

$scope.regconf = function() {
        $window.location.href = '/#/registrationConfirm';
      }
$scope.cancel = function() {
       //uzikrinimo lentele ir tada delete jei paspaudzia taip
     }
});

appControllers.controller('registrationConfirmController', function($scope, $http, $rootScope, $window) {

//$rootScope.regData = { "name":name, "surname": surname, "phone":phone, "email": email, "unit":unit, "date": date,
//            "topic": topic, "comment": comment}//, "user": rootScope.user }
document.getElementById("nameField").innerHTML = $rootScope.regData.name; //"Vardenis";
document.getElementById("surnameField").innerHTML = $rootScope.regData.surname; //"Pavardenis";
document.getElementById("phoneField").innerHTML = $rootScope.regData.phone; //"+370 12345678";
document.getElementById("emailField").innerHTML = $rootScope.regData.email; //"pavardenis@gmail.com";
document.getElementById("unitSelect").innerHTML = $rootScope.regData.unit; //"ozo g. 25(PPC Akropolis)";
document.getElementById("dateField").innerHTML = $rootScope.regData.date; //"2016-03-19";
document.getElementById("topicSelect").innerHTML = $rootScope.regData.topic; //"PAskolos, lizingas";
document.getElementById("commentField").innerHTML = $rootScope.regData.comment; //"Paskolos suteikimas užsienyje studijuojnčiam studentui";



$scope.cancel = function() {
            $window.location.href = '/#/overview';
            }
});