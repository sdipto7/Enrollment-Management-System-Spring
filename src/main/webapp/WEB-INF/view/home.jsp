<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
    <h1><c:out value="WELCOME ${currentUser.name}"/></h1><br>

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
