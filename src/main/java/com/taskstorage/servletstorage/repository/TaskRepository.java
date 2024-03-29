package com.taskstorage.servletstorage.repository;

import com.taskstorage.servletstorage.model.Task;

import java.sql.*;
import java.util.ArrayList;

public class TaskRepository {

    private Connection connection;

    public TaskRepository() {
        connection = ConnectorFactory.getConnection();
    }

    public ArrayList<Task> selectAll() {

        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task");
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                Long user_id = resultSet.getLong("user_id");

                Task task = new Task(id, description, content, user_id);
                tasks.add(task);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return tasks;
    }

    public Task selectById(Long id) {

        Task task = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM task WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                Long user_id = resultSet.getLong("user_id");

                task = new Task(id, description, content, user_id);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return task;
    }

    public void create(Task task) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task (description, content, user_id) Values (?, ?, ?)");
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setString(2, task.getContent());
            preparedStatement.setLong(3, task.getAuthor());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(Task task) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE task SET description = ?, content = ? WHERE id = ?");
            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setString(2, task.getContent());
            preparedStatement.setLong(3, task.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void delete(Long id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM task WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}