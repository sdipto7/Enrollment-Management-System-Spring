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
    <title>Admin Home</title>
</head>
<body>
    <h2><spring:message code="page.header"/> </h2>

    <c:url var="courseViewLink" value="/course">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${courseViewLink}"><c:out value="View Courses"/></a>
    <br>
    <br>

    <c:url var="userViewLink" value="/user">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${userViewLink}"><c:out value="View Users"/></a>
    <br>
    <br>

    <c:url var="enrollmentViewLink" value="/enrollment">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${enrollmentViewLink}"><c:out value="View Enrollments"/></a>
    <br>
    <br>

    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>
