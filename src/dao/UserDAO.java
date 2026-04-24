package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(String username, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password); // ideally hashed

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean validateUser(String username, String password) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT password FROM users WHERE username=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
            return false;
        }
    }
}