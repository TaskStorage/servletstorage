### Servlet Storage
Реализация простого сервиса хранения заметок.

Основные применяемые технологии:
- Servlet
- JSP + Bootstrap
- JDBC
- Maven

Для запуска необходимо поменять имя базы данных и данные пользователя в файле 
[application.properties](https://github.com/TaskStorage/servletstorage/blob/master/src/main/resources/application.properties)
а также создать таблицы используя [db.sql](https://github.com/TaskStorage/servletstorage/blob/master/src/main/resources/db.sql)

Приложение позволяет:

- Создавать редактировать и удалять заметки
- Предоставляет механизм регистрации, авторизации и управления пользователями

