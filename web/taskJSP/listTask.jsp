<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<%@include file="/parts/head.jsp" %>
<body>
<%@include file="/parts/navbar.jsp" %>
<div class="container mb-5 mt-3">

    <h2>Tasks List</h2>
<p><a href='<c:url value="?action=create"/>'>Create new</a></p>
<table>
    <tr>
        <th>Description</th>
        <th>Content</th>
        <th></th>
    </tr>
    <c:forEach var="task" items="${tasks}">
        <tr>
            <td>${task.description}</td>
            <td>${task.content}</td>
            <td>
                <a href='<c:url value="?action=edit&id=${task.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="?action=delete"/>' style="display:inline;">
                    <input type="hidden" name="id" value="${task.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href='<c:url value="/logout"/>'>Logout</a></p>

</div>
<%@include file="/parts/footer.jsp" %>
</body>
</html>