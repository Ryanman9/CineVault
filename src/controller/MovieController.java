package controller;

import java.sql.SQLException;

import utils.Validator;
import java.util.List;
import dao.MovieDAO;
import model.Movie;

public class MovieController 
{
    private final MovieDAO movieDAO = new MovieDAO();

    public String addMovie(String title, String genre, String yearText, String ratingText)
    {
        if (Validator.isBlank(title) || Validator.isBlank(genre) || Validator.isBlank(yearText) || Validator.isBlank(ratingText)) {
            return "All fields are required.";
        }

        int year;
        double rating;

        try {
            year = Integer.parseInt(yearText.trim());
            rating = Double.parseDouble(ratingText.trim());
        } catch (NumberFormatException e) {
            return "Year and rating must be numeric.";
        }

        if (!Validator.isValidMovieYear(year)) {
            return "Year is out of valid range.";
        }

        Movie movie = new Movie(title.trim(), genre.trim(), year, rating);

        try {
            return movieDAO.addMovie(movie) ? "SUCCESS" : "Movie could not be added.";
        } catch (SQLException e) {
            return "Unable to add movie: " + e.getMessage();
        }
    }

    public boolean removeMovie(int movieId)
    {
        try {
            return movieDAO.deleteMovie(movieId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMovieRating(int movieId, double newRating)
    {
        try {
            return movieDAO.updateMovieRating(movieId, newRating);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Movie> fetchAllMovies()
    {
        try {
            return movieDAO.fetchAllMovies();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
