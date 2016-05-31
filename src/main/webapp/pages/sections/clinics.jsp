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
    <section class="nav nav-pills">
        <form name="navForm" role="form"
              ng-submit="clinics.getClinics()"
              novalidate>
            <label>
                <s:message code="animalType"/>
                <select class="form-control" ng-model="clinics.animalType" required>

                <option ng-repeat="option in clinics.animalTypeList"
                        value="{{$index}}">{{option}}
                </option>
                </select>
            </label>
            <label>
                <s:message code="district"/>
                <select class="form-control" ng-model="clinics.district" required>
                    <option ng-repeat="option in clinics.districtList"
                            value="{{$index}}">{{option}}
                    </option>
                </select>
            </label>
            <input class="btn btn-default" type="submit" value="<s:message code="Submit"/>">
        </form>
            <section>
                <h4><s:message code="Clinics"/></h4>

                <div id="map" class="col-sm-10">

                </div>
                <blockquote ng-repeat="clinic in clinics.clinicList">
                    <b class="clearfix"><s:message code="ClinicName"/> : {{clinic.title}}</b>
                    <span class="clearfix"><s:message code="Description"/> : {{clinic.description}}</span>
                    <span class="clearfix"><s:message code="Region"/> : {{clinic.district}}</span>
                    <span class="clearfix"><s:message code="Address"/> : {{clinic.address}}</span>
                    <s:message code="Contacts"/> :
                    <ul>
                        <li ng-repeat="email in clinic.contactEmails">
                            {{email}}
                        </li>
                    </ul>
                    <s:message code="Animals"/> :
                    <ul>
                        <li ng-repeat="animal in clinic.animalTypes">
                            {{animal}}
                        </li>
                    </ul>

                    <div class="btn btn-block"
                         ng-click="clinics.toddleShowComments(clinic)">
                        <s:message code="Comments"/>
                    </div>
                    <div ng-show="clinic.showComments">
                        <blockquote ng-repeat="comment in clinic.comments">
                            <h6><s:message code="SentBy"/> : {{comment.user.login}} {{comment.creationDate | date}}</h6>
                            {{comment.text}}<br/>
                            <s:message code="Mark"/> : {{comment.mark}}
                        </blockquote>
                        <sec:authorize access="isAuthenticated()">
                            <form name="commentForm" role="form"
                                  ng-controller="CommentController as commentCtrl"
                                  ng-submit="
                              commentCtrl.addComment(clinics, clinic)"
                                  novalidate>
                                <select class="form-control" ng-model="commentCtrl.comment.mark"
                                        required>
                                    <option value="1"><s:message code="1star"/></option>
                                    <option value="2"><s:message code="2stars"/></option>
                                    <option value="3"><s:message code="3stars"/></option>
                                    <option value="4"><s:message code="4stars"/></option>
                                    <option value="5"><s:message code="5stars"/></option>
                                </select>
                            <textarea placeholder="<s:message code="YourComment"/>" class="form-control"
                                      ng-model="commentCtrl.comment.text" required></textarea>
                                <input class="btn btn-default" type="submit" value="<s:message code="Submit"/>"
                                       class="form-control"/>

                            </form>
                        </sec:authorize>

                    </div>
                </blockquote>
            </section>

    </section>
</section>