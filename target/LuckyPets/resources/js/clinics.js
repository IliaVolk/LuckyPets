/**
 * Created by user on 19.04.2016.
 */

/*
 clinics module
 responds for displaying list of clinics
 */
(function () {

    //creating angular module
    var app = angular.module('clinics', []);

    //test controller
    //TODO: delete
    app.controller('HelloController', function () {
        this.hello = 'hello';
    });

    //clinics controller
    //
    app.controller('ClinicsController', ['$http',//injection of $http service
        //$http service is used for loading content with ajax
        function ($http) {
            //saving reference of 'this' in variable for
            //using in callback functions
            //variables are registered on it for IDE support
            var self = this;
            //list of clinics for display
            self.clinicList = [];
            //variable for saving current selected in form animal type
            self.animalType = '';
            //list of all animal types for displaying in <select> tag
            //loaded from server
            self.animalTypeList = [];
            //selected district
            self.district = '';
            //district list, like animalTypeList
            self.districtList = [];
            //click listener for button "show\hide comments"
            self.toddleShowComments = function (clinic) {
                //if there no comments, loads them from server
                //and sets clinic.showComments for true
                //clinic.showComments - flag, if true - comments displayed
                //if false - no
                if (!clinic.comments) {
                    self.getComments(clinic);
                    clinic.showComments = true;
                }
                //toddles showComments flag
                else {
                    clinic.showComments = !clinic.showComments;
                }
            };
            //function for loading comments for clinic
            self.getComments = function (clinic) {
                //uses $http for creating get request in server with clinicId param as clinic.id
                //should be better way to do this
                //TODO: find better way
                //success - registered callback function
                //sets loaded comments as property of clinic
                $http.get('/ajax/clinics?clinicId=' + clinic.id).success(function (data) {
                    clinic.comments = data;
                });
            };
            //loading animal type list
            //request to controller controller.ajax.GeneralController
            //method with request mapping "/ajax/animalList"
            $http.get('/ajax/animalList').success(function (data) {
                self.animalTypeList = data;
            });
            //loading district list
            //like previous
            $http.get('/ajax/districtList').success(function (data) {
                self.districtList = data;
            });
            //click listener for <input type="submit"> button in
            //clinic type selecting form
            //loads clinics for search criteria
            self.getClinics = function () {
                //$http.post now takes 2 parameters:
                //1. request url
                //2. request object
                //request object becomes a parameter of controller.ajax.ClinicsController
                //method public List<Clinic> getClinicsByAnimalType
                //object is JSON representation of
                //entity.ajax.AjaxClinicByAnimalTypeAndDistrictRequest
                $http.post('/ajax/clinics', {
                    beginIndex: 0,
                    count: 10,
                    animalType: self.animalType,
                    district: self.district
                }).success(function (data) {
                    self.clinicList = data;
                });
            };
        }]);

    app.directive('clinicsList', function(){
     return {
         restrict: 'E',
         templateUrl: 'sections/clinics',
         controller: 'ClinicsController',
         controllerAs: 'clinics'
     };
     });
})();