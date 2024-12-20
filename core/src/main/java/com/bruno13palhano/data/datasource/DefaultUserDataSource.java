package com.bruno13palhano.data.datasource;

import com.bruno13palhano.User;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class DefaultUserDataSource implements UserDataSource {
    private final Connection connection;

    public DefaultUserDataSource(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(User user) {
        String QUERY = "REPLACE INTO users (uid, username, email, password, photo, phone, address, city, role, enabled, " +
                "time_stamp) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setString(1, user.getUid());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBytes(5, user.getPhoto());
            preparedStatement.setString(6, user.getPhone());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getCity());
            preparedStatement.setString(9, user.getRole());
            preparedStatement.setBoolean(10, user.getEnabled());
            preparedStatement.setString(11, user.getTimestamp());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String QUERY = "UPDATE users SET username = ?, photo = ?, phone = ?, address = ?, city = ?, time_stamp = ? " +
                "WHERE uid = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setBytes(2, user.getPhoto());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getCity());
            preparedStatement.setString(6, user.getTimestamp());
            preparedStatement.setString(7, user.getUid());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String uid) {
        String QUERY = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, uid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByUsername(String username) {
        User user = null;
        String QUERY = "SELECT * FROM users WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            user = new User(
                    resultSet.getString("uid"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    "",
                    resultSet.getBytes("photo"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("city"),
                    resultSet.getString("role"),
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("time_stamp")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public Boolean usernameAlreadyExist(String username) {
        String QUERY = "SELECT username FROM users WHERE username = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getString("username").equals(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean emailAlreadyExist(String email) {
        String QUERY = "SELECT email FROM users WHERE email = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return resultSet.getString("email").equals(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        String QUERY = "SELECT * FROM users WHERE email = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            user = new User(
                    resultSet.getString("uid"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    "",
                    resultSet.getBytes("photo"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("city"),
                    resultSet.getString("role"),
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("time_stamp")

            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getById(String uid) {
        User user = null;
        String QUERY = "SELECT * FROM users WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.setString(1, uid);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            user = new User(
                    resultSet.getString("uid"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    "",
                    resultSet.getBytes("photo"),
                    resultSet.getString("phone"),
                    resultSet.getString("address"),
                    resultSet.getString("city"),
                    resultSet.getString("role"),
                    resultSet.getBoolean("enabled"),
                    resultSet.getString("time_stamp")

            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
