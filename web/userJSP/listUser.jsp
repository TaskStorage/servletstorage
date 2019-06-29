<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<h2>User List</h2>
<p><a href='<c:url value="/register" />'>Create new</a></p>
<table>
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
                <%--<td>--%>
                <%--<a href='<c:url value="/edit?id=${task.id}" />'>Edit</a> |--%>
                <%--<form method="post" action='<c:url value="/delete" />' style="display:inline;">--%>
                <%--<input type="hidden" name="id" value="${task.id}">--%>
                <%--<input type="submit" value="Delete">--%>
                <%--</form>--%>
                <%--</td>--%>
        </tr>
    </c:forEach>
</table>
<p><a href='<c:url value="/" />'>Main</a></p>
</body>
</html>