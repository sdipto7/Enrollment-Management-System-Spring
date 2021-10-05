<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title><spring:message code="user.title.edit"/></title>
</head>
<body>
    <form:form action="/user" modelAttribute="user" method="post">
        <table>
            <c:if test="${id != 0}">
                <tr>
                    <td>
                        <form:hidden path="id" />
                        <form:hidden path="created" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message code="user.label.name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" />
                </td>
            </tr>
            <form:errors path="name"/>
            <tr>
                <td>
                    <form:label path="role">
                        <spring:message code="user.label.role"/>
                    </form:label>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="role">
                        <spring:message code="prompt.admin"/>
                    </form:label>
                    <form:radiobutton path="role" value="Admin"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="role">
                        <spring:message code="prompt.user"/>
                    </form:label>
                    <form:radiobutton path="role" value="User"/>
                </td>
            </tr>
            <form:errors path="role"/>
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="<spring:message code="prompt.save"/>" />
                </td>
            </tr>
        </table>
    </form:form>
    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>
