<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container" style="width: 300px;">
    <sec:authorize access="!isAuthenticated()">
        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h2 class="form-signin-heading"><s:message code="SignIn"/></h2>
            <!--<label for="login">Login: </label>-->
            <input id="login" type="text" class="form-control" name="j_username"
                   placeholder="<s:message code="Email"/> " required autofocus><br/>
            <!--<label for="password">Password: </label>-->
            <input id="password" type="password" class="form-control" name="j_password"
                   placeholder="<s:message code="Password"/> " required>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><s:message code="doSignIn"/></button>

        </form>
        <s:url value="/user" var="registrationUrl">
            <s:param name="new" value=""/>
        </s:url>
        <a href="${registrationUrl}"><strong>Зарегистрироваться!</strong></a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h1><s:message code="Hello"/> , <sec:authentication property="principal.username"/></h1>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
    </sec:authorize>

</div>
