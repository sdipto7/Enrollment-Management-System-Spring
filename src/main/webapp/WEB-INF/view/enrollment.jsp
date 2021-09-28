<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title>Edit Enrollment</title>
</head>
<body>
    <c:if test="${action == 'update'}">
        <c:out value="User Name: ${enrollment.user.name}"/><br>
        <c:out value="Course Code: ${enrollment.course.courseCode}"/><br><br><br>
        <label>Update Information:</label><br><br>
    </c:if>
    <form action="/enrollment" method="post">
        <input type="hidden" name="enrollmentId" value="${enrollmentId}"></input>
        <label for="UserName"> User's Name: </label>
        <input id="UserName" type="text" name="name"></input><br><br>
        <label for="CourseCode"> Course Code: </label>
        <input id="CourseCode" type="text" name="courseCode"></input><br><br>
        <input type="submit" name="action" value="${action}"></input><br><br>
    </form>

    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>