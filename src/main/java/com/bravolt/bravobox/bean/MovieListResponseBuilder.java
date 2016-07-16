package com.bravolt.bravobox.bean;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;

import com.bravolt.bravobox.constants.MovieConstants;
import com.bravolt.bravobox.model.movie.MovieListResponse;
import com.bravolt.bravobox.properties.MovieProperties;
import com.google.gson.Gson;

public class MovieListResponseBuilder {

	public void toMovieListResponse(Exchange exchange) throws IOException {
		CSVParser parser = CSVParser.parse(new File(String.format("%s/%s", MovieProperties.get("inventory.dir"),
				MovieProperties.get("inventory.fileName"))), StandardCharsets.US_ASCII, CSVFormat.DEFAULT);
		List<MovieListResponse> list = new ArrayList<MovieListResponse>();
		Gson gson = new Gson();
		String requestMovieType = (String)exchange.getProperty(MovieConstants.MOVIE_TYPE);

		int i = 0;
		for (CSVRecord csvRecord : parser.getRecords()) {
			List<String> types = Arrays.asList(((String) csvRecord.get(2)).split(";"));
			
			if( i == 0 ) {
				i++;
				continue;
			}

			for (String type : types) {
				if (StringUtils.isEmpty(requestMovieType) || type.equals(requestMovieType)) {
					MovieListResponse response = new MovieListResponse();

					response.setImdbId(csvRecord.get(0));
					response.setType(csvRecord.get(2));
					response.setTitle(csvRecord.get(1));
					
					list.add(response);

					break;
				}
			}
			
		}
		
		exchange.getOut().setBody(gson.toJson(list));
	}

}
