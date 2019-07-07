<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<h2>Tasks List</h2>
<p><a href='<c:url value="?action=create" />'>Create new</a></p>
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
                <form method="post" action='<c:url value="?action=delete" />' style="display:inline;">
                    <input type="hidden" name="id" value="${task.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href='<c:url value="/" />'>Main</a></p>
</body>
</html>