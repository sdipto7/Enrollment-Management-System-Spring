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
    <form:form action="loginForm" method="post" modelAttribute="credential">
        <table>
            <tr>
                <td>
                    <form:label path="userName">
                        <spring:message text="Username" />
                    </form:label>
                </td>
                <td>
                    <form:input path="userName" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message text="Password" />
                    </form:label>
                </td>
                <td>
                    <form:input path="password" />
                </td>
            </tr>
        </table>
        <input type="submit" value="<spring:message code="login"/>">
    </form:form>
</body>
</html>