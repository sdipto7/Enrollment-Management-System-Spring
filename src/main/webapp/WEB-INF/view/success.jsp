<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--
* @author rumi.dipto
* @since 10/3/21
--%>
<html>
<head>
    <title><spring:message code="success.title"/></title>
</head>
<body>
    <h2><c:out value="${entity} is Successfully ${operation}"/></h2>
    <a href="/home"><spring:message code="success.hyperlink.home"/></a>

    <c:url var="courseViewUrl" value="/course">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${courseViewUrl}"><spring:message code="prompt.viewCourse"/></a>

    <c:url var="userViewUrl" value="/user">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${userViewUrl}"><spring:message code="prompt.viewUser"/></a>

    <c:url var="enrollmentViewUrl" value="/enrollment">
        <c:param name="action" value="view"/>
    </c:url>
    <a href="${enrollmentViewUrl}"><spring:message code="prompt.viewEnrollment"/></a>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>
