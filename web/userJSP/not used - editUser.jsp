<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit info</title>
</head>
<body>
<h3>Edit info</h3>
<form method="post">
    <input type="hidden" value="${user.id}" name="id"/>
    <label>Username</label><br>
    <input name="username" value="${user.username}"/><br><br>
    <label>Content</label><br>
    <input name="password" value="${user.password}"/><br><br>

    <input type="radio" name="role" id="USER" value="USER" checked/><label for="USER">USER</label><br><br>
    <input type="radio" name="role" id="ADMIN" value="ADMIN"/><label for="ADMIN">ADMIN</label><br><br>

    <input type="submit" value="Send"/>
</form>
</body>
</html>