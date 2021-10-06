<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="login.title"/></title>
</head>
<body>
    <form:form action="login" method="post" commandName="credential">
        <table>
            <tr>
                <td>
                    <form:label path="userName">
                        <spring:message code="prompt.username" />
                    </form:label>
                </td>
                <td>
                    <form:input path="userName" />
                </td>
            </tr>
            <form:errors path="userName" />
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message code="prompt.password" />
                    </form:label>
                </td>
                <td>
                    <form:input path="password" />
                </td>
            </tr>
            <form:errors path="password" />
        </table>
        <input type="submit" value="<spring:message code="prompt.login"/>">
    </form:form>
</body>
</html>