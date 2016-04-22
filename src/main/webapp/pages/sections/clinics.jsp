<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 22.04.2016
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<section>
    <sec:authorize access="isAuthenticated()">
        <h1><s:message code="Hello"/>, <sec:authentication property="principal.username"/></h1>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <h1>Аутенцифицируйтесь пожалуйста</h1>
    </sec:authorize>
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
                        value="{{$index}}">{{option}}
                </option>
            </select>
            <!--as previos-->
            <select class="form-control" ng-model="clinics.district" required>
                <option ng-repeat="option in clinics.districtList"
                        value="{{$index}}">{{option}}
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
                                Date: {{comment.creationDate | date}}
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