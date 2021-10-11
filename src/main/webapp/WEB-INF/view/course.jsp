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
    <title><spring:message code="course.title.edit"/></title>
    <script type="text/javascript">
        function showAlert() {
            if (!(confirm('Are you sure to delete the selected course ?'))) {
                return false;
            }
        }
    </script>
</head>
<body>
<form:form action="/course" modelAttribute="course" method="post">
    <form:hidden path="id"/>
    <form:hidden path="created"/>
    <table>
        <tr>
            <td>
                <form:label path="courseCode">
                    <spring:message code="course.label.code"/>
                </form:label>
            </td>
            <td>
                <form:input path="courseCode"/>
                <form:errors path="courseCode"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="courseTitle">
                    <spring:message code="course.label.title"/>
                </form:label>
            </td>
            <td>
                <form:input path="courseTitle"/>
                <form:errors path="courseTitle"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit" name="action" value="SAVE"><spring:message code="prompt.save"/></button>
            </td>
        </tr>
        <c:if test="${course.id != 0}">
            <tr>
                <td colspan="2">
                    <button type="submit" name="action" value="DELETE" onclick="showAlert()"><spring:message code="prompt.delete"/></button>
                </td>
            </tr>
        </c:if>
    </table>
</form:form>
<c:url var="logoutUrl" value="/logout"/>
<a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>