package com.bravolt.bravobox.bean;

import org.apache.camel.Exchange;

import com.bravolt.bravobox.model.movie.Movie;
import com.google.gson.Gson;

public class MovieBuilder {
	
	public void toJson(Exchange exchange) {
		Gson gson = new Gson();
		
		exchange.getOut().setBody(gson.toJson(exchange.getIn(Movie.class)));
	}

}
