<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title><spring:message code="enrollment.title"/></title>
</head>
<body>
    <form:form action="/enrollment" modelAttribute="enrollment" method="post">
        <table>
            <c:if test="${id != 0}">
                <tr>
                    <td>
                        <form:hidden path="id" />
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="user">
                        <spring:message text="User's name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="user" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="course">
                        <spring:message text="Course Code"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="course" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="<spring:message text="Save"/>" />
                </td>
            </tr>
        </table>
    </form:form>
    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>