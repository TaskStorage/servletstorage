<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit task</title>
</head>
<body>
<h3>Edit task</h3>
<form method="post">
    <input type="hidden" value="${task.id}" name="id"/>
    <label>Description</label><br>
    <input name="description" value="${task.description}"/><br><br>
    <label>Content</label><br>
    <input name="content" value="${task.content}"/><br><br>
    <input type="submit" value="Send"/>
</form>
</body>
</html>