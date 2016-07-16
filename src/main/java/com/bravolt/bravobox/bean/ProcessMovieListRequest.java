package com.bravolt.bravobox.bean;

import org.apache.camel.Exchange;

import com.bravolt.bravobox.constants.MovieConstants;
import com.bravolt.bravobox.model.movie.MovieListRequest;
import com.google.gson.Gson;

public class ProcessMovieListRequest {
	
	public void toMovieListRequest(Exchange exchange) {
		Gson gson = new Gson();
		MovieListRequest movieListRequest = gson.fromJson(exchange.getIn().getBody(String.class), MovieListRequest.class);
		
		exchange.setProperty(MovieConstants.MOVIE_TYPE, movieListRequest.getType());
	}

}
