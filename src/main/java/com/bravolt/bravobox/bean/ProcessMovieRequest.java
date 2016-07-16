package com.bravolt.bravobox.bean;

import org.apache.camel.Exchange;

import com.bravolt.bravobox.model.movie.MovieRequest;
import com.google.gson.Gson;

public class ProcessMovieRequest {
	
	public void toMovieRequest(Exchange exchange) {
		Gson gson = new Gson();
		String json = exchange.getIn().getBody(String.class);
		MovieRequest movieReq = gson.fromJson(json, MovieRequest.class);
		
		exchange.getIn().setHeader(Exchange.HTTP_QUERY, String.format("i=%s", movieReq.getImdbMovie()));
	}

}
