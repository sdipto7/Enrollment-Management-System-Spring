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
    <title><spring:message code="courseList.title"/></title>
    <script type="text/javascript">
        function showAlert() {
            if (!(confirm('Are you sure to delete the selected course ?'))) return false;
        }
    </script>
</head>
<body>
<%--<h3><c:out value="${currentUser.role}"/></h3>--%>
    <table>
        <tr>
            <th><spring:message code="courseList.table.header1"/></th>
            <th><spring:message code="courseList.table.header2"/></th>
        </tr>
        <c:forEach var="course" items="${courseList}">
            <c:url var="updateUrl" value="/course">
                <c:param name="courseId" value="${course.id}"/>
                <c:param name="action" value="edit"/>
            </c:url>
            <c:url var="deleteUrl" value="/course">
                <c:param name="courseId" value="${course.id}"/>
                <c:param name="action" value="delete"/>
            </c:url>
            <tr>
                <td><c:out value="${course.courseCode}"/></td>
                <td><c:out value="${course.courseTitle}"/></td>
<%--                <c:if test="${currentUser.role == 'ADMIN'}">--%>
                    <td>
                        <a href="${updateUrl}"><c:out value="Edit"/></a>
                        |
                        <a href="${deleteUrl}" onclick="showAlert()"><c:out value="Delete"/></a>
                    </td>
<%--                </c:if>--%>
            </tr>
        </c:forEach>
    </table>
    <br>
<%--    <c:if test="${currentUser.role == 'ADMIN'}">--%>
        <c:url var="addUrl" value="/course">
            <c:param name="action" value="edit"/>
            <c:param name="courseId" value="0"/>
        </c:url>
        <a href="${addUrl}"><c:out value="Add Course"/></a>
        <br><br>
<%--    </c:if>--%>

    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>