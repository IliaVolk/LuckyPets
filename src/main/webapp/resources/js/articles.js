/**
 * Created by user on 30.05.2016.
 */

(function () {

    var app = angular.module("articles", ["clinics", "adverts"]);


    var PanelControllerConstructor = function () {
        this.tab = 1;
        this.selectTab = function (setTab) {
            this.tab = setTab;
        };
        this.isSelected = function (checkTab) {
            return this.tab === checkTab;
        };
    };

    var CommentControllerConstructor = function () {
        this.comment = {};
        this.addComment = function (controller, object) {
            //this.comment.creationDate = Date.now();
            controller.addComment(object, this.comment);
            //product.reviews.push(this.review);
            this.comment = {};
        };

    };
    app.controller("PanelController", PanelControllerConstructor);
    app.controller("CommentController", CommentControllerConstructor);

    app.directive("comments", function () {
        return {
            restrict: "E",
            templateUrl: "sections/comments"
        }
    });

})();