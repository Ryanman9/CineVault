package controller;

import dao.RatingDAO;
import model.Movie;

import java.sql.SQLException;
import java.util.List;

public class RatingController {

    private final RatingDAO ratingDAO = new RatingDAO();

    public List<Movie> getMoviesByRating(double rating) {
        try {
            return ratingDAO.getMoviesByRating(rating);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}