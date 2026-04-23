package utils;

import model.User;

public class SessionManager {
    private static User currentUser;

    private SessionManager() {
    }

    public static void startSession(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void clearSession() {
        currentUser = null;
    }
}