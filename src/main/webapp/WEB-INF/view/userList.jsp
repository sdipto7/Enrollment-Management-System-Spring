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
    <title><spring:message code="userList.title"/></title>
</head>
<body>
    <table cellpadding="10px" border="1px">
        <tr>
            <th><spring:message code="user.label.name"/></th>
            <c:if test="${currentUser.role == 'ADMIN'}">
                <th><spring:message code="user.label.role"/></th>
            </c:if>
        </tr>
        <c:forEach var="user" items="${userList}">
            <c:url var="editUrl" value="/user">
                <c:param name="userId" value="${user.id}"/>
            </c:url>
            <tr>
                <td><c:out value="${user.name}"/></td>
                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <a href="${editUrl}"><spring:message code="prompt.edit"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${currentUser.role == 'ADMIN'}">
        <c:url var="addUrl" value="/user"/>
        <a href="${addUrl}"><spring:message code="prompt.add"/></a>
    </c:if>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>
