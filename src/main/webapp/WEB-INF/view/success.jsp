<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%--
* @author rumi.dipto
* @since 10/3/21
--%>
<html>
<head>
    <title><spring:message code="success.title"/></title>
</head>
<body>
    <h2><spring:message code="success.message"/></h2>
    <a href="/home"><spring:message code="success.hyperlink.home"/></a>
</body>
</html>
