<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<%@include file="/parts/head.jsp" %>
<body>
<%@include file="/parts/navbar.jsp" %>
<div class="container mb-5 mt-3">

    <h3>Edit task</h3>
<form method="post">
    <input type="hidden" value="${task.id}" name="id"/>
    <label>Description</label><br>
    <input name="description" value="${task.description}"/><br><br>
    <label>Content</label><br>
    <input name="content" value="${task.content}"/><br><br>
    <input type="submit" value="Send"/>
</form>

</div>
<%@include file="/parts/footer.jsp" %>
</body>
</html>