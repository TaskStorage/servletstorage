package com.taskstorage.servletstorage;

import java.sql.*;
import java.util.ArrayList;

public class TaskDBWorker {

    private static String url = "jdbc:mysql://localhost:3306/storage?useSSL=false&useUnicode=yes&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String username = "TaskStorage";
    private static String password = "8AC4uGkbgzcnGjcr";

    public static ArrayList<Task> selectAll() {

        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks");
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String description = resultSet.getString("description");
                    String content = resultSet.getString("content");
                    Task task = new Task(id, description, content);
                    tasks.add(task);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return tasks;
    }

    public static Task selectOne(Long id) {

        Task task = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM tasks WHERE id=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setLong(1, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        Long searchId = resultSet.getLong("id");
                        String description = resultSet.getString("description");
                        String content = resultSet.getString("content");
                        task = new Task(searchId, description, content);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return task;
    }

    public static int create(Task task) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO tasks (description, content) Values (?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, task.getDescription());
                    preparedStatement.setString(2, task.getContent());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(Task task) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "UPDATE tasks SET description = ?, content = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, task.getDescription());
                    preparedStatement.setString(2, task.getContent());
                    preparedStatement.setLong(3, task.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int delete(Long id) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "DELETE FROM tasks WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setLong(1, id);
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }
}