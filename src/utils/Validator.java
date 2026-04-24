package utils;

import java.time.Year;

public class Validator {
    private Validator() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidUsername(String username) {
        return !isBlank(username) && username.trim().length() >= 3;
    }

    public static boolean isValidPassword(String password) {
        return !isBlank(password) && password.length() >= 4;
    }

    public static boolean isValidMovieYear(int year) {
        int currentYear = Year.now().getValue();
        return year >= 1888 && year <= currentYear + 1;
    }

    public static boolean isValidRating(int rating) {
        return rating >= 1 && rating <= 5;
    }
}