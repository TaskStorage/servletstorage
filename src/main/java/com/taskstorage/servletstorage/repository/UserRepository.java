package com.taskstorage.servletstorage.repository;

import com.taskstorage.servletstorage.model.Role;
import com.taskstorage.servletstorage.model.User;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository {

    private Connection connection;

    public UserRepository() {
        connection = ConnectorFactory.getConnection();
    }

    public ArrayList<User> selectAll() {

        ArrayList<User> users = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.
                    executeQuery("SELECT * FROM user");
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    Role role = Role.valueOf(resultSet.getString("role"));
                    User user = new User(id, username, password, role);
                    users.add(user);
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return users;
    }

    public User selectById(Long id) {

        User user = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM user WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Role role = Role.valueOf(resultSet.getString("role"));
                user = new User(id, username, password, role);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    public void create(User user) {

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO user (username, password, role) Values (?, ?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, Role.USER.name());
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void update(User user) {

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("UPDATE user SET username = ?, password = ?, role = ? WHERE id = ?");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole().name());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void delete(Long id) {

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("DELETE FROM user WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public User selectByUsername(String username) {

        User user = null;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM user WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return user;
    }
}