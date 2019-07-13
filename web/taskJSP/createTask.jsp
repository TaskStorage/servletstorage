<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<%@include file="/parts/head.jsp" %>
<body>
<%@include file="/parts/navbar.jsp" %>
<div class="container mb-5 mt-3">

    <h3>New task</h3>
<form method="post">
    <label>Description</label><br>
    <input name="description"/><br><br>
    <label>Content</label><br>
    <input name="content"/><br><br>
    <input type="submit" value="Save"/>
</form>

</div>
<%@include file="/parts/footer.jsp" %>
</body>
</html>