package com.bravolt.bravobox.bean;

import java.io.IOException;

import org.apache.camel.Exchange;

import com.bravolt.bravobox.dao.MovieRepository;
import com.bravolt.bravobox.model.movie.RentMovieRequest;
import com.bravolt.bravobox.model.movie.RentMovieResponse;
import com.google.gson.Gson;

public class RentMovie {

  private MovieRepository movieRepository;

  public RentMovie() {
    this.movieRepository = new MovieRepository();
  }

  public void rentMovie(Exchange exchange) throws IOException {
    Gson gson = new Gson();
    String json = exchange.getIn().getBody(String.class);
    RentMovieRequest rentMovieRequest = gson.fromJson(json, RentMovieRequest.class);
    RentMovieResponse rentMovieResponse = new RentMovieResponse();
    boolean isApprovedRental = false;

    if (!movieRepository.doesMovieExist(rentMovieRequest.getImdbId())) {
      rentMovieResponse.setApproved(false);
      exchange.getOut().setBody(gson.toJson(rentMovieResponse), String.class);
      return;
    }

    try {
      movieRepository.rentMovie(rentMovieRequest);
      isApprovedRental = true;
    } catch (Exception e) {
      isApprovedRental = false;
    }

    rentMovieResponse.setApproved(isApprovedRental);
    exchange.getOut().setBody(gson.toJson(rentMovieResponse), String.class);
  }
}
