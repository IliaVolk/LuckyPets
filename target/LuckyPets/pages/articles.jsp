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
        <clinics-list></clinics-list>
    </div>


</div>