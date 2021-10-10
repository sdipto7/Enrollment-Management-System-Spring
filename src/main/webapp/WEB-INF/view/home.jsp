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
    <h2><spring:message code="home.header"/>
        <c:out value="${currentUser.name}"/>
    </h2>

    <c:if test="${not empty success}">
        <h3>${success}</h3>
    </c:if>

    <c:url var="courseViewUrl" value="/course/list">
        <c:param name="action" value="VIEW"/>
    </c:url>
    <a href="${courseViewUrl}"><spring:message code="prompt.viewCourse"/></a>

    <c:url var="userViewUrl" value="/user">
        <c:param name="action" value="VIEW"/>
    </c:url>
    <a href="${userViewUrl}"><spring:message code="prompt.viewUser"/></a>

    <c:url var="enrollmentViewUrl" value="/enrollment">
        <c:param name="action" value="VIEW"/>
    </c:url>
    <a href="${enrollmentViewUrl}"><spring:message code="prompt.viewEnrollment"/></a>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>
