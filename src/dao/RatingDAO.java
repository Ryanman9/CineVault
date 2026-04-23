package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Movie;

public class RatingDAO
{
    public List<Movie> getMoviesByRating(double rating) throws SQLException
    {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id, title, genre, year, rating FROM movies WHERE rating >= ?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, rating);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("genre"),
                    rs.getInt("year"),
                    rs.getDouble("rating")
                );
                movies.add(movie);
            }
        }

        return movies;
    }
}