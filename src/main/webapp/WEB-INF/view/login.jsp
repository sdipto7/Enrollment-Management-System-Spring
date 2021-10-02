<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%--
* @author rumi.dipto
* @since 9/10/21
--%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

    <form:form action="loginForm" method="post" modelAttribute="credential">
        <form:label path="userName">
            <spring:message text="Username" />
        </form:label>
        <form:input path="userName" />

        <form:label path="password">
            <spring:message text="Password" />
        </form:label>
        <form:input path="password" />

        <input type="submit" value="<spring:message text="login"/>">
    </form:form>
<%--    <form action="loginForm" method="post">--%>
<%--        <label> Username: <input type="text" name="userName"></label><br><br>--%>
<%--        <label> Password: <input type="password" name="password"></label><br><br>--%>
<%--        <input type="submit" value="login">--%>
<%--    </form>--%>
</body>
</html>