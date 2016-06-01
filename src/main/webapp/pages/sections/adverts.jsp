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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section>
    <h1><s:message code="Adverts"/></h1>
    <section class="nav nav-pills">
        <form name="navForm" role="form"
              ng-submit="adverts.getAdverts()"
              novalidate>
            <label>
                <s:message code="animalType"/>
                <select class="form-control" ng-model="adverts.animalType" required>

                    <option ng-repeat="option in adverts.animalTypeList"
                            value="{{$index}}">{{option}}
                    </option>
                </select>
            </label>
            <label>
                <s:message code="advertType"/>
                <select class="form-control" ng-model="adverts.advertType" required>
                    <option ng-repeat="option in adverts.advertTypeList"
                            value="{{$index}}">{{option}}
                    </option>
                </select>
            </label>
            <input class="btn btn-default" type="submit" value="<s:message code="Submit"/>">
        </form>
        <section>
            <h4><s:message code="Adverts"/></h4>
            <blockquote ng-repeat="advert in adverts.advertList">
                <b class="clearfix"><h2>{{advert.title}}</h2></b>
                <span class="clearfix">{{advert.text}}</span>
                <span class="clearfix"><s:message code="Type"/> : {{advert.advertType}}</span>
                <s:message code="Animals"/> :
                <ul>
                    <li ng-repeat="animal in advert.animalTypes">
                        {{animal}}
                    </li>
                </ul>

                <div class="btn btn-block"
                     ng-click="adverts.toddleShowComments(advert)">
                    <s:message code="Comments"/>
                </div>
                <div ng-show="advert.showComments">
                    <blockquote ng-repeat="comment in advert.comments">
                        <h6><s:message code="SentBy"/> : {{comment.user.login}} {{comment.creationDate | date}}</h6>
                        {{comment.text}}
                    </blockquote>
                    <sec:authorize access="isAuthenticated()">
                        <form name="commentForm" role="form"
                              ng-controller="CommentController as commentCtrl"
                              ng-submit="
                               commentCtrl.addComment(adverts, advert)"
                              novalidate>
                            <textarea placeholder="<s:message code="YourComment"/> " class="form-control"
                                      ng-model="commentCtrl.comment.text" required></textarea>
                            <input type="submit" value="<s:message code="Submit"/>"
                                   class="btn btn-default"/>
                        </form>
                    </sec:authorize>


                </div>
            </blockquote>
        </section>
        </form>
    </section>
</section>