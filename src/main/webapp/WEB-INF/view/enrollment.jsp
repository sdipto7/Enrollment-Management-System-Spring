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
    <title><spring:message code="enrollment.title.edit"/></title>
    <script type="text/javascript">
        function showAlert() {
            if (!(confirm('Are you sure to delete the selected enrollment ?'))) {
                return false;
            }
        }
    </script>
</head>
<body>
    <form:form action="/enrollment" modelAttribute="enrollment" method="post">
        <form:hidden path="id"/>
        <form:hidden path="created"/>
        <table>
            <tr>
                <td>
                    <form:label path="user">
                        <spring:message code="prompt.user"/>
                    </form:label>
                </td>
                <td>
                    <form:select path = "user">
                        <form:options items="${userList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="user" />
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="course">
                        <spring:message code="prompt.course"/>
                    </form:label>
                </td>
                <td>
                    <form:select path = "course">
                        <form:options items="${courseList}" itemValue="id" itemLabel="courseCode"/>
                    </form:select>
                    <form:errors path="course" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" name="action" value="SAVE"><spring:message code="prompt.save"/></button>
                </td>
            </tr>
            <c:if test="${enrollment.id != 0}">
                <tr>
                    <td colspan="2">
                        <button type="submit" name="action" value="DELETE" onclick="showAlert()"><spring:message code="prompt.delete"/></button>
                    </td>
                </tr>
            </c:if>
        </table>
    </form:form>
    <c:url var="logoutUrl" value="/logout"/>
    <a href="${logoutUrl}"><spring:message code="prompt.logout"/></a>
</body>
</html>