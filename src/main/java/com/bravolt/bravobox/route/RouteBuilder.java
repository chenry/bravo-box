package com.bravolt.bravobox.route;

import org.apache.camel.model.dataformat.CsvDataFormat;

import com.bravolt.bravobox.bean.MovieListResponseBuilder;
import com.bravolt.bravobox.bean.ProcessMovieListRequest;
import com.bravolt.bravobox.bean.ProcessMovieRequest;
import com.bravolt.bravobox.bean.RentMovie;

public class RouteBuilder extends org.apache.camel.builder.RouteBuilder {

  //@formatter:off
	@Override
	public void configure() throws Exception {
		CsvDataFormat csv = new CsvDataFormat();
		
		csv.setDelimiter(",");
		
		from("properties:inbound.movie.information")
			.bean(ProcessMovieRequest.class)
			.to("properties:outbound.movie.information");
		
		from("properties:inbound.movie.list")
			.bean(ProcessMovieListRequest.class)
			.bean(MovieListResponseBuilder.class)
				.setProperty("inventoryDir").simple("properties:inventory.dir")
				.setProperty("inventoryFileName").simple("properties:inventory.fileName");
		
		from("properties:inbound.movie.rent")
		    .bean(RentMovie.class);
	
	}
        //@formatter:on
}
