<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.06.2016
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <h1><s:message code="AddAdvert"/></h1>

    <form name="commentForm" role="form"
          ng-submit="saveAdvertCtrl.saveAdvert()"
          novalidate>
        <input class="form-control" ng-model="saveAdvertCtrl.advert.title"
               placeholder="<s:message code="Title" />" required>
        <label>
            <s:message code="animalType"/>
            <select class="form-control" ng-model="saveAdvertCtrl.advert.animalType" required>

                <option ng-repeat="option in adverts.animalTypeList"
                        value="{{$index}}">{{option}}
                </option>
            </select>
        </label>
        <label>
            <s:message code="advertType"/>
            <select class="form-control" ng-model="saveAdvertCtrl.advert.advertType" required>
                <option ng-repeat="option in adverts.advertTypeList"
                        value="{{$index}}">{{option}}
                </option>
            </select>
        </label>
    <textarea class="form-control" ng-model="saveAdvertCtrl.advert.text"
              placeholder="<s:message code="Description"/> " required></textarea>

        <input class="btn btn-default" type="submit" value="<s:message code="Submit"/>"
               class="form-control"/>
    </form>
    <div>{{saveAdvertCtrl.message}}</div>
</section>
