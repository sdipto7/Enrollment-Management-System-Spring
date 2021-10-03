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
    <title><spring:message code="home.title"/></title>
</head>
<body>
    <h2><spring:message code="home.header"/> </h2>
    <c:url var="courseViewUrl" value="/course">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${courseViewUrl}"><spring:message code="success.hyperlink.viewCourse"/></a>

    <c:url var="userViewUrl" value="/user">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${userViewUrl}"><spring:message code="success.hyperlink.viewUser"/></a>

    <c:url var="enrollmentViewUrl" value="/enrollment">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${enrollmentViewUrl}"><spring:message code="success.hyperlink.viewEnrollment"/></a>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><c:out value="Logout"/></a>
</body>
</html>
