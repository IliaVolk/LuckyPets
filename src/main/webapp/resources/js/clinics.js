/**
 * Created by user on 19.04.2016.
 */

/*
 clinics module
 responds for displaying list of clinics
 */
(function () {

    var app = angular.module("clinics", []);

    app.controller("ClinicsController", ["$http",
        function ($http) {
            var self = this;
            self.clinicList = [];
            self.animalType = "";
            self.animalTypeList = [];
            self.district = "";
            self.districtList = [];
            self.toddleShowComments = function (clinic) {
                if (!clinic.comments) {
                    self.getComments(clinic);
                    clinic.showComments = true;
                }
                else {
                    clinic.showComments = !clinic.showComments;
                }
            };
            self.addComment = function (clinic, comment) {
                $http.post("/ajax/clinics/" + clinic.id + "/comments", comment).
                    success(function (processedComment) {
                        if (!clinic.comments) {
                            clinic.comments = [];
                        }
                        clinic.comments.push(processedComment);
                    });
            };
            self.getComments = function (clinic) {

                $http.get("/ajax/clinics/" + clinic.id + "/comments").success(function (data) {
                    clinic.comments = data;
                });
            };

            $http.get("/ajax/animalList").success(function (data) {
                self.animalTypeList = data;
            });

            $http.get("/ajax/districtList").success(function (data) {
                self.districtList = data;
            });

            self.getClinics = function () {

                $http.get("/ajax/clinics/" + self.animalType + "/" + self.district + "/" + 0 + "/" + 10)
                    .success(function (data) {
                        self.clinicList = data;
                        initMap(self.clinicList);
                    });
            };
        }]);

    app.directive("clinicsList", function () {
     return {
         restrict: "E",
         templateUrl: "sections/clinics",
         controller: "ClinicsController",
         controllerAs: "clinics"
     };
     });
})();