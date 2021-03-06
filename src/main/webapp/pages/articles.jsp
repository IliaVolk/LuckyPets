<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="container" ng-app="articles" ng-controller="PanelController as panel">
    <ul class="nav nav-pills">
        <li ng-class="{active:panel.isSelected(1)}"><a href ng-click="panel.selectTab(1)"><s:message
                code="Clinics"/></a></li>
        <li ng-class="{active:panel.isSelected(2)}"><a href ng-click="panel.selectTab(2)"><s:message
                code="Adverts"/></a></li>
        <sec:authorize access="isAuthenticated()">
            <li ng-class="{active:panel.isSelected(3)}"><a href ng-click="panel.selectTab(3)"><s:message
                    code="AddAdvert"/></a></li>
            <li ng-class="{active:panel.isSelected(4)}"><a href ng-click="panel.selectTab(4)"><s:message
                    code="SeeAnswers"/></a></li>
        </sec:authorize>

    </ul>
    <div class="panel" ng-show="panel.isSelected(1)">
        <clinics-list></clinics-list>
    </div>
    <div class="panel" ng-show="panel.isSelected(2)">
        <adverts-list></adverts-list>
    </div>
    <sec:authorize access="isAuthenticated()">
        <div class="panel" ng-show="panel.isSelected(3)">
            <add_advert></add_advert>
        </div>
        <div class="panel" ng-show="panel.isSelected(4)">
            <adverts_answers></adverts_answers>
        </div>
    </sec:authorize>



</div>