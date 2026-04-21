package controller;

import dao.RatingDAO;

public class RatingController 
{
    RatingDAO rating = new RatingDAO();
    public void getMovies(Double Rating)
    {
        rating.getMovies(Rating);
    }
}
