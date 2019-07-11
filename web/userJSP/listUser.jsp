<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<h2>User List</h2>
<table>
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th>Role</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td>
                <a href='<c:url value="?action=edit&id=${user.id}" />'>Edit</a> |
                <form method="post" action='<c:url value="?action=delete"/>' style="display:inline;">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<div>Curredt user id - ${currentUserId}</div>
<p><a href='<c:url value="/logout"/>'>Logout</a></p>
<p><a href='<c:url value="/"/>'>Main</a></p>
</body>
</html>