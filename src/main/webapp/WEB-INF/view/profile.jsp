<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--
* @author rumi.dipto
* @since 10/27/21
--%>
<html>
<head>
    <title><spring:message code="profile.title"/></title>
</head>
<body>
    <h2>
        <spring:message code="prompt.welcome"/>
        <c:out value="${currentUser.name}"/>
    </h2>

    <form:form action="/user/updatePassword" modelAttribute="user" method="post">
        <table>
            <tr>
                <td>
                    <form:label path="password">
                        <spring:message code="user.label.password"/>
                    </form:label>
                </td>
                <td>
                    <form:password path="password"/>
                    <form:errors path="password" cssStyle="color: red"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" value="SAVE"><spring:message code="prompt.save"/></button>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
