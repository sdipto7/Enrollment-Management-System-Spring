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
    <title><spring:message code="course.title"/></title>
</head>
<body>
    <form:form action="/course" modelAttribute="course">
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
                    <form:label path="courseCode">
                        <spring:message text="Course Code"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="courseCode" />
                </td>
            </tr>
            <tr>
                <form:errors path="courseCode"/>
            </tr>
            <tr>
                <td>
                    <form:label path="courseTitle">
                        <spring:message text="Course Title"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="courseTitle" />
                </td>
            </tr>
            <tr>
                <form:errors path="courseTitle"/>
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