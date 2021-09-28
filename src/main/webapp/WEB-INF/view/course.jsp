<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title>Edit Course</title>
</head>
<body>
    <c:if test="${action == 'update'}">
        <c:out value="Code: ${course.courseCode}"/><br>
        <c:out value="Title: ${course.courseTitle}"/><br><br><br>
        <label>Update Information:</label><br>
    </c:if>
    <form action="/course" method="post">
        <input type="hidden" value="${courseId}" name="courseId"></input>
        <label for="courseCode">Code:</label>
        <input id="courseCode" type="text" name="courseCode"></input><br><br>
        <label for="courseTitle"> Title:</label>
        <input id="courseTitle" type="text" name="courseTitle"></input><br><br>
        <input type="submit" name="action" value="${action}"></input><br><br>
    </form>

    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>