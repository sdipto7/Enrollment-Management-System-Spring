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
    <title><spring:message code="enrollment.title.edit"/></title>
</head>
<body>
    <form:form action="/enrollment" modelAttribute="enrollment" method="post">
        <c:if test="${id != 0}">
            <form:hidden path="id" />
            <form:hidden path="created" />
        </c:if>
        <table>
            <tr>
                <td>
                    <form:label path="user">
                        <spring:message code="prompt.user"/>
                    </form:label>
                </td>
                <td>
                    <form:select path = "user">
                        <form:options items="${userList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="user" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="course">
                        <spring:message code="prompt.course"/>
                    </form:label>
                </td>
                <td>
                    <form:select path = "course">
                        <form:options items="${courseList}" itemValue="id" itemLabel="courseCode"/>
                    </form:select>
                    <form:errors path="course" />
                </td>
            </tr>
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