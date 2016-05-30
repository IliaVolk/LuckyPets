<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="text-center">
    <h2><s:message code="CreateNewAccount"/></h2>

    <sf:form method="POST" modelAttribute="user"
            ><!--enctype allows to send images
            each field would be sent as a particular part of POST request-->
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="username"><s:message code="Email"/> :</label></th>
                <td>
                    <sf:input path="login" size="15" maxlength="15" id="username"/>
                    <sf:errors path="login" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <th><label for="password"><s:message code="Password"/> :</label></th>
                <td><sf:password path="password" size="30"
                                 showPassword="true" id="password"/>
                    <sf:errors path="password" cssClass="error"/>
                </td>
            </tr>


            <tr>
                <th></th>
                <td><input name="commit" type="submit"
                           value="<s:message code="CreateAccount"/> "/></td>
            </tr>
        </table>
    </fieldset>
    </sf:form>
</div>