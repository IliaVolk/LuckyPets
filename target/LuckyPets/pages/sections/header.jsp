<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 29.03.2016
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
  <h1>Header</h1>
  <spring:message code="Hello" text="default text"/>
  Current locale: ${pageContext.response.locale}
  ${Hello}

</div>