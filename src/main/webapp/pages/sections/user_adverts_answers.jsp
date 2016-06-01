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
    <h1><s:message code="Answers"/></h1>

    <form ng-submit="answersCtrl.getComments()">
        <input type="submit" value="Submit">
    </form>
    <blockquote ng-repeat="comment in answersCtrl.comments">
        <h6><s:message code="SentBy"/> : {{comment.user.login}} {{comment.creationDate | date}}</h6>
        {{comment.text}}
        <hr/>
        <s:message code="AnsweredFor"/>:<br/>
        <blockquote>
            <span><b>{{comment.advert.title}}</b></span><br/>
            <span>{{comment.advert.text}}</span>
        </blockquote>
    </blockquote>
</section>
