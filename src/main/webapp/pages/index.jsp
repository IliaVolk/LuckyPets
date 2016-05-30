<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

    <h1>Devcolibri.com</h1>
        <p class="lead">Devcolibri - это сервис предоставляющий всем желающим возможность обучаться программированию.</p>
        <s:url value="/foradmin" var="adminUrl"/>
        <sec:authorize url="/foradmin">
            <a href="${adminUrl}">for admin</a>
        </sec:authorize>
    </div>

    <div class="footer">
        <p>&copy; Devcolibri 2014</p>
    </div>

</div>

