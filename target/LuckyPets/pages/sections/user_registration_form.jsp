<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div>
    <h2>Create a free Spitter account</h2>

    <sf:form method="POST" modelAttribute="user"
            ><!--enctype allows to send images
            each field would be sent as a particular part of POST request-->
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="username">Username:</label></th>
                <td>
                    <sf:input path="login" size="15" maxlength="15" id="username"/>
                    <sf:errors path="login" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <th><label for="password">Password:</label></th>
                <td><sf:password path="password" size="30"
                                 showPassword="true" id="password"/>
                    <sf:errors path="password" cssClass="error"/>
                </td>
            </tr>


            <tr>
                <th></th>
                <td><input name="commit" type="submit"
                           value="I accept. Create my account."/></td>
            </tr>
        </table>
    </fieldset>
    </sf:form>
</div>