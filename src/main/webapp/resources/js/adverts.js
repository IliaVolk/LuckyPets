/**
 * Created by user on 30.05.2016.
 */



(function () {


    var app = angular.module("adverts", []);

    app.controller("AdvertsController", ["$http",
        function ($http) {
            var self = this;
            self.advertList = [];
            self.animalType = "";
            self.animalTypeList = [];
            self.advertType = "";
            self.advertTypeList = [];
            self.toddleShowComments = function (advert) {
                if (!advert.comments) {
                    self.getComments(advert);
                    advert.showComments = true;
                }
                else {
                    advert.showComments = !advert.showComments;
                }
            };
            self.addComment = function (advert, comment) {
                $http.post("/ajax/adverts/" + advert.id + "/comments", comment).
                    success(function (processedComment) {
                        if (!advert.comments) {
                            advert.comments = [];
                        }
                        advert.comments.push(processedComment);
                    });
            };
            self.getComments = function (advert) {

                $http.get("/ajax/adverts/" + advert.id + "/comments").success(function (data) {
                    advert.comments = data;
                });
            };

            $http.get("/ajax/animalList").success(function (data) {
                self.animalTypeList = data;
            });

            $http.get("/ajax/advertTypeList").success(function (data) {
                self.advertTypeList = data;
            });

            self.getAdverts = function () {

                $http.get("/ajax/adverts/" + self.animalType + "/" + self.advertType + "/" + 0 + "/" + 10)
                    .success(function (data) {
                        self.advertList = data;
                    });
            };
        }]);

    app.directive("advertsList", function () {
        return {
            restrict: "E",
            templateUrl: "sections/adverts",
            controller: "AdvertsController",
            controllerAs: "adverts"
        };
    });

})();