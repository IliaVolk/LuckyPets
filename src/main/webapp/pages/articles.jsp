<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container">
    <!--ng-app attribute specifies that this block is under angular.js, module 'clinics'-->
    <div ng-app="clinics">
        <div>
            <input type="text" ng-model="hello">
            {{hello}}
        </div>
        <!-- this block is under controller ClinicsController with alias 'clinics'
        alias used inside of block-->
        <section ng-controller="ClinicsController as clinics">
            <section class="nav nav-pills">
                <!--ng-submit - any type of submit this form will call only javascript code in
                  quoted
                  navForm.$valid - angular validation, adds some donuts(css classes, ets)-->
                <form name="navForm" role="form"
                      ng-submit="navForm.$valid && clinics.getClinics()"
                      novalidate>
                    <!--ng-model - links <select> selected value with variable clinics.animal type,
                    defined in controller.
                    if there are no variable defined, it will be created, even global-->
                    <select class="form-control" ng-model="clinics.animalType" required>
                        <!--cycle in property
                        like
                        for (var option in clinics.animalTypeList){}
                        expression {{option}} displays value of javascript variable option,
                        defined in scope-->
                        <option ng-repeat="option in clinics.animalTypeList"
                                value="{{option}}">{{option}}
                        </option>
                    </select>
                    <!--as previos-->
                    <select class="form-control" ng-model="clinics.district" required>
                        <option ng-repeat="option in clinics.districtList"
                                value="{{option}}">{{option}}
                        </option>
                    </select>
                    <input type="submit" value="Submit">
                    <section>
                        <h4>Clinics</h4>
                        <blockquote ng-repeat="clinic in clinics.clinicList">
                            <b class="clearfix">Name: {{clinic.title}}</b>
                            <span class="clearfix">Desc: {{clinic.description}}</span>
                            <span class="clearfix">District: {{clinic.district}}</span>
                            <span class="clearfix">Address: {{clinic.address}}</span>
                            <span class="clearfix">LatLng: {{clinic.latLng.lat}}, {{ clinic.latLng.lng}}</span>
                            Emails:
                            <ul>
                                <li ng-repeat="email in clinic.contactEmails">
                                    {{email}}
                                </li>
                            </ul>
                            Animals:
                            <ul>
                                <li ng-repeat="animal in clinic.animalTypes">
                                    {{animal}}
                                </li>
                            </ul>
                            <!--ng-click - any click on this block will call javascript code in quotes
                            calls method and passes parameter to it, defined in upper tag <section>-->
                            <div class="btn btn-block"
                                 ng-click="clinics.toddleShowComments(clinic)">
                                Comments
                            </div>
                            <!--ng-show - displays tag if only value in quoted equals to true-->
                            <div ng-show="clinic.showComments">
                                <ul>
                                    <li ng-repeat="comment in clinic.comments">
                                        Id: {{comment.id}}
                                        Data: {{comment.creationDate}}
                                        Text: {{comment.text}}
                                        User: {{comment.user.login}}
                                        Mark: {{comment.mark}}
                                    </li>
                                </ul>
                            </div>
                        </blockquote>
                    </section>
                </form>
            </section>
        </section>
    </div>


</div>