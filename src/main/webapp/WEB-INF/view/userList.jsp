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
    <script type="text/javascript">
        function showAlert() {
            if (!(confirm('Are you sure to delete the selected user ?'))) {
                return false;
            }
        }
    </script>
</head>
<body>
    <table>
        <tr>
            <th><spring:message code="userList.table.header1"/></th>
            <c:if test="${currentUser.role == 'ADMIN'}">
                <th><spring:message code="userList.table.header2"/></th>
            </c:if>
        </tr>
        <c:forEach var="user" items="${userList}">
            <c:url var="updateUrl" value="/user">
                <c:param name="userId" value="${user.id}"/>
                <c:param name="action" value="save"/>
            </c:url>
            <c:url var="deleteUrl" value="/user">
                <c:param name="userId" value="${user.id}"/>
                <c:param name="action" value="delete"/>
            </c:url>
            <tr>
                <td><c:out value="${user.name}"/></td>
                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <a href="${updateUrl}"><spring:message code="update"/></a>
                        |
                        <a href="${deleteUrl}" onclick="showAlert()"> <spring:message code="delete"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:if test="${currentUser.role == 'ADMIN'}">
        <c:url var="addUrl" value="/user">
            <c:param name="action" value="save"/>
            <c:param name="userId" value="0"/>
        </c:url>
        <a href="${addUrl}"><spring:message code="add"/></a>
        <br><br>
    </c:if>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="logout"/></a>
</body>
</html>
