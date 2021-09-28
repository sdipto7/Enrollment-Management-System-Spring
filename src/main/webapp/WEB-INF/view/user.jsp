<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <c:if test="${action == 'update'}">
        <c:out value="Name: ${user.name}"/><br>
        <c:out value="Role: ${user.role}"/><br><br><br>
        <label>Update Information:</label><br><br>
    </c:if>
    <form action="/user" method="post">
        <input type="hidden" name="userId" value="${userId}"></input>
        <label for="Name"> Name: </label>
        <input id="Name" type="text" name="name"></input><br><br>
        <label> Role: </label><br>
        <label for="Admin"> ADMIN</label>
        <input id="Admin" type="radio" name="role" value="admin"></input><br>
        <label for="User"> USER</label>
        <input id="User" type="radio" name="role" value="user"></input><br><br>
        <input type="submit" name="action" value="${action}"></input><br><br>
    </form>

    <c:url var="logoutLink" value="/logout"/>
    <a href="${logoutLink}"><c:out value="Logout"/></a>
</body>
</html>
