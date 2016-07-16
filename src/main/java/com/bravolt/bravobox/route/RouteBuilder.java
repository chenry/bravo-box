package com.bravolt.bravobox.route;

import org.apache.camel.model.dataformat.CsvDataFormat;

import com.bravolt.bravobox.bean.FindLateMovies;
import com.bravolt.bravobox.bean.MovieListResponseBuilder;
import com.bravolt.bravobox.bean.ProcessMovieListRequest;
import com.bravolt.bravobox.bean.ProcessMovieRequest;
import com.bravolt.bravobox.bean.RentMovie;
import com.bravolt.bravobox.bean.ReturnMovie;
import com.bravolt.bravobox.bean.SetupEmailProperties;

public class RouteBuilder extends org.apache.camel.builder.RouteBuilder {

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
		
		from("properties:inbound.notify.late")
			.bean(FindLateMovies.class)
			.split(body())
			.bean(SetupEmailProperties.class)
			.to("properties:late.email");
		
		from("properties:inbound.rent")
			.bean(RentMovie.class);
		
		from("properties:inbound.return")
			.bean(ReturnMovie.class);
	}
}
