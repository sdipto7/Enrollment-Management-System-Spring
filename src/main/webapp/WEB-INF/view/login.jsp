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
    <h2><c:out value="${errorMessage}"/> </h2>
    <form:form action="login" method="post" commandName="credentialCommand">
        <table>
            <tr>
                <td>
                    <form:label path="userName">
                        <spring:message code="prompt.username" />
                    </form:label>
                </td>
                <td>
                    <form:input path="userName"/>
                    <form:errors path="userName"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message code="prompt.password" />
                    </form:label>
                </td>
                <td>
                    <form:password path="password"/>
                    <form:errors path="password"/>
                </td>
            </tr>
        </table>
        <button type="submit" value="LOGIN"><spring:message code="prompt.login"/></button>
    </form:form>
</body>
</html>