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
</head>
<body>
    <table cellpadding="10px" border="1px">
        <tr>
            <th><spring:message code="user.label.name"/></th>
            <th><spring:message code="course.label.code"/></th>
            <th><spring:message code="course.label.title"/></th>
        </tr>
        <c:forEach var="enrollment" items="${enrollmentList}">
            <c:url var="editUrl" value="/enrollment">
                <c:param name="enrollmentId" value="${enrollment.id}"/>
            </c:url>
            <tr>
                <td><c:out value="${enrollment.user.name}"/></td>
                <td><c:out value="${enrollment.course.courseCode}"/></td>
                <td><c:out value="${enrollment.course.courseTitle}"/></td>
                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td>
                        <a href="${editUrl}"><spring:message code="prompt.update"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${currentUser.role == 'ADMIN'}">
        <c:url var="addUrl" value="/enrollment"/>
        <a href="${addUrl}"><spring:message code="prompt.add"/></a>
    </c:if>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>
