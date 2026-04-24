package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import model.Movie;

public class MovieDAO
{    
    public boolean addMovie(Movie movie) throws SQLException 
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "INSERT INTO movies(title, genre, year, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getGenre());
            stmt.setInt(3, movie.getYear());
            stmt.setDouble(4, movie.getRating());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateMovieRating(int movieId, double newRating) throws SQLException
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "UPDATE movies SET rating=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setDouble(1, newRating);
            stmt.setInt(2, movieId);

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deleteMovie(int movieId) throws SQLException
    {
        try (Connection conn = DBConnection.getConnection())
        {
            String query = "DELETE FROM movies WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, movieId);

            return stmt.executeUpdate() > 0;
        }
    }
    
    public List<Movie> fetchAllMovies() throws SQLException
    {
        List<Movie> movies = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection())
        {
            String query = "SELECT * FROM movies";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Movie movie = new Movie(rs.getInt("id"),rs.getString("title"),rs.getString("genre"),rs.getInt("year"),rs.getDouble("rating"));
                movies.add(movie);
            }
        }

        return movies;
    }
    
}