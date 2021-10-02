<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title>Enrollments</title>
    <script type="text/javascript">
        function showAlert() {
            if (!(confirm('Are you sure to delete the selected enrollment ?'))) {
                return false;
            }
        }
    </script>
</head>
<body>
    <table>
        <tr>
            <th>User name</th>
            <th>Course Code</th>
            <th>Course Title</th>
        </tr>
        <c:forEach var="enrollment" items="${enrollmentList}">
            <c:url var="updateUrl" value="/enrollment">
                <c:param name="action" value="edit"/>
                <c:param name="enrollmentId" value="${enrollment.id}"/>
            </c:url>
            <c:url var="deleteUrl" value="/enrollment">
                <c:param name="action" value="delete"/>
                <c:param name="enrollmentId" value="${enrollment.id}"/>
            </c:url>
            <tr>
                <td><c:out value="${enrollment.user.name}"/></td>
                <td><c:out value="${enrollment.course.courseCode}"/></td>
                <td><c:out value="${enrollment.course.courseTitle}"/></td>
                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td>
                        <a href="${updateUrl}"><c:out value="Edit"/></a>
                        |
                        <a href="${deleteUrl}" onclick="showAlert()"> <c:out value="Delete"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:if test="${currentUser.role == 'ADMIN'}">
        <c:url var="addLink" value="/enrollment">
            <c:param name="action" value="edit"/>
            <c:param name="enrollmentId" value="0"/>
        </c:url>
        <a href="${addLink}"><c:out value="Add Enrollment"/></a>
        <br><br>
    </c:if>

    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>
