package controller;

import dao.UserDAO;
import model.User;
import utils.SessionManager;

import java.sql.SQLException;

public class AuthController {

    private final UserDAO userDAO = new UserDAO();

    public String login(String username, String password)
    {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return "Username and password are required.";
        }

        try {
            boolean valid = userDAO.validateUser(username.trim(), password.trim());

            if (valid) {
                SessionManager.startSession(new User(username.trim(), null));
                return "SUCCESS";
            } else {
                return "Invalid username or password.";
            }

        } catch (SQLException e) {
            return "Login error: " + e.getMessage();
        }
    }

    public String register(String username, String password)
    {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return "Username and password are required.";
        }

        try {
            boolean success = userDAO.registerUser(username.trim(), password.trim());
            return success ? "SUCCESS" : "Registration failed.";
        } catch (SQLException e) {
            return "Registration error: " + e.getMessage();
        }
    }

    public void logout() {
        SessionManager.clearSession();
    }
}