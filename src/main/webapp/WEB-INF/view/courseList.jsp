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
    <title><spring:message code="courseList.title"/></title>
    <script type="text/javascript">
        function showAlert() {
            if (!(confirm('Are you sure to delete the selected course ?'))) return false;
        }
    </script>
</head>
<body>
    <table>
        <tr>
            <th><spring:message code="course.label.code"/></th>
            <th><spring:message code="course.label.title"/></th>
        </tr>
        <c:forEach var="course" items="${courseList}">
            <c:url var="updateUrl" value="/course">
                <c:param name="courseId" value="${course.id}"/>
                <c:param name="action" value="save"/>
            </c:url>
            <c:url var="deleteUrl" value="/course">
                <c:param name="courseId" value="${course.id}"/>
                <c:param name="action" value="delete"/>
            </c:url>
            <tr>
                <td><c:out value="${course.courseCode}"/></td>
                <td><c:out value="${course.courseTitle}"/></td>
                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td>
                        <a href="${updateUrl}"><spring:message code="prompt.update"/></a>
                        |
                        <a href="${deleteUrl}" onclick="showAlert()"><spring:message code="prompt.delete"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:if test="${currentUser.role == 'ADMIN'}">
        <c:url var="addUrl" value="/course">
            <c:param name="action" value="save"/>
            <c:param name="courseId" value="0"/>
        </c:url>
        <a href="${addUrl}"><spring:message code="prompt.add"/></a>
        <br><br>
    </c:if>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>