<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container" style="width: 300px;">
    <sec:authorize access="!isAuthenticated()">
        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="form-control" name="j_username" placeholder="Email address" required autofocus value="colibri">
            <
            <input type="password" class="form-control" name="j_password" placeholder="Password" required value="1234">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
        </form>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p>Ваш логин: <sec:authentication property="principal.username" /></p>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>

    </sec:authorize>
</div>
