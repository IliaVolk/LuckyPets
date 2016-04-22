<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="container" style="width: 300px;">
    <sec:authorize access="!isAuthenticated()">
        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form action="${loginUrl}" method="post">
            <h2 class="form-signin-heading">Please sign in</h2>
            <!--<label for="login">Login: </label>-->
            <input id="login" type="text" class="form-control" name="j_username"
                   placeholder="Email address" required autofocus><br/>
            <!--<label for="password">Password: </label>-->
            <input id="password" type="password" class="form-control" name="j_password"
                   placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            <label for="remember_me">Remember me</label>
            <input id="remember_me" type="checkbox" placeholder="Remember me"
                   name=""/><!--must be implemented on frontend-->
        </form>
        <s:url value="/user" var="registrationUrl">
            <s:param name="new" value=""/>
        </s:url>
        <a href="${registrationUrl}"><strong>Зарегистрироваться!</strong></a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h1>Ваш логин: <sec:authentication property="principal.username"/></h1>
        <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_USER')">
        <h1>You are user!</h1>
    </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h1>You are admin!</h1>
    </sec:authorize>
</div>
