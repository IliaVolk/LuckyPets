/**
 * Created by user on 01.06.2016.
 */

/**
 * Created by user on 30.05.2016.
 */



(function () {


    var app = angular.module("user_actions", []);

    app.controller("AdvertsAnswersController", ["$http",
        function ($http) {
            var self = this;
            self.comments = [];
            this.getComments = function () {
                $http.get("/ajax/users/adverts/comments/0/10").success(function (data) {
                    self.comments = data;
                });
            };
            self.getComments();

        }]);

    app.controller("SaveAdvertController", ["$http",
        function ($http) {
            var self = this;
            this.advert = {};
            this.animalTypeList = [];
            this.advertTypeList = [];
            this.message = "";
            this.saveAdvert = function () {

                $http.post("/ajax/adverts/" + self.advert.animalType + "/" + self.advert.advertType,
                    {
                        title: self.advert.title,
                        text: self.advert.text
                    }).then(
                    function success(responce) {
                        self.message = "Сохранено";
                        self.advert = {};
                    },
                    function error(responce) {
                        self.message = "Чтото пошло не так..."
                    }
                );
            };
            $http.get("/ajax/animalList").success(function (data) {
                self.animalTypeList = data;
            });
            $http.get("/ajax/advertTypeList").success(function (data) {
                self.advertTypeList = data;
            });
        }]);

    app.directive("advertsAnswers", function () {
        return {
            restrict: "E",
            templateUrl: "sections/user_adverts_answers",
            controller: "AdvertsAnswersController",
            controllerAs: "answersCtrl"
            //ng-controller="AdvertsAnswersController as answersCtrl"
        };
    });
    app.directive("addAdvert", function () {
        return {
            restrict: "E",
            templateUrl: "sections/add_advert",
            controller: "SaveAdvertController",
            controllerAs: "saveAdvertCtrl"
        };
    });
})();