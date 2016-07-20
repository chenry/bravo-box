package com.bravolt.bravobox.dao;

import com.bravolt.bravobox.model.movie.RentMovieRequest;

public class MovieRepository {

  public boolean doesMovieExist(String imdbId) {
    // determine if the movie actually exists
    return true;
  }

  public void rentMovie(RentMovieRequest rentMovieRequest) {
    // update the movie database appropriately
    System.out.println("Attempting to rent movie: " + rentMovieRequest);
  }

}
