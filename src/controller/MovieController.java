package controller;

import dao.MovieDAO;

public class MovieController 
{
    MovieDAO movie = new MovieDAO();
    public void addMovie(String title, String genre, int year, double rating)
    {
        movie.addMovie(title, genre, year, rating);        
    }

    public void removeMovie(int movieId)
    {
        movie.deleteMovie(movieId);
    }

    public void updateMovieRating(int movieId, double newRating)
    {
        movie.updateMovieRating(movieId, newRating);
    }

    public void fetchAllMovies()
    {
        movie.fetchAllMovies();
    }
}
