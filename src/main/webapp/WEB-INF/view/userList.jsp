<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
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
<h2><c:out value="${currentUser.role}" /> </h2>
    <table>
        <tr>
            <th><spring:message code="userList.table.header1"/></th>
<%--            <c:if test="${currentUser.role == 'ADMIN'}">--%>
                <th><spring:message code="userList.table.header2"/></th>
<%--            </c:if>--%>
        </tr>
        <c:forEach var="user" items="${userList}">
            <c:url var="updateUrl" value="/user">
                <c:param name="userId" value="${user.id}"/>
                <c:param name="action" value="edit"/>
            </c:url>
            <c:url var="deleteUrl" value="/user">
                <c:param name="userId" value="${user.id}"/>
                <c:param name="action" value="delete"/>
            </c:url>
            <tr>
                <td><c:out value="${user.name}"/></td>
<%--                <c:if test="${currentUser.role == 'ADMIN'}">--%>
                    <td><c:out value="${user.role}"/></td>
                    <td>
                        <a href="${updateUrl}"><c:out value="Edit"/></a>
                        |
                        <a href="${deleteUrl}" onclick="showAlert()"> <c:out value="Delete"/></a>
                    </td>
<%--                </c:if>--%>
            </tr>
        </c:forEach>
    </table>
    <br>
<%--    <c:if test="${currentUser.role == 'ADMIN'}">--%>
        <c:url var="addUrl" value="/user">
            <c:param name="action" value="edit"/>
            <c:param name="userId" value="0"/>
        </c:url>
        <a href="${addUrl}"><c:out value="Add User"/></a>
        <br><br>
<%--    </c:if>--%>

    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><c:out value="Logout"/></a>
</body>
</html>
