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
    <title><spring:message code="enrollmentList.title"/></title>
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
            <th><spring:message code="user.label.name"/></th>
            <th><spring:message code="course.label.code"/></th>
            <th><spring:message code="course.label.title"/></th>
        </tr>
        <c:forEach var="enrollment" items="${enrollmentList}">
            <c:url var="updateUrl" value="/enrollment">
                <c:param name="action" value="save"/>
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
        <c:url var="addUrl" value="/enrollment">
            <c:param name="action" value="save"/>
            <c:param name="enrollmentId" value="0"/>
        </c:url>
        <a href="${addUrl}"><spring:message code="prompt.add"/></a>
        <br><br>
    </c:if>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>
