package com.bravolt.bravobox.bean;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;

import com.bravolt.bravobox.model.movie.MovieRentalRequest;
import com.bravolt.bravobox.properties.MovieProperties;
import com.google.gson.Gson;

public class RentMovie {

	public void rentMovie(Exchange exchange) throws IOException {
		Gson gson = new Gson();
		MovieRentalRequest request = gson.fromJson(exchange.getIn().getBody(String.class), MovieRentalRequest.class);
		File movieDb = new File(String.format("%s/%s", MovieProperties.get("inventory.dir"),
				MovieProperties.get("inventory.fileName")));
		CSVParser parser = CSVParser.parse(movieDb, StandardCharsets.US_ASCII, CSVFormat.DEFAULT);
		List<String> records = new ArrayList<>();

		if (StringUtils.isEmpty(request.getImdbId())) {
			request.setApproved(Boolean.FALSE);
			exchange.getOut().setBody(gson.toJson(request));
			return;
		}

		for (CSVRecord record : parser.getRecords()) {
			String imdbId = record.get(0);
			String title = record.get(1);
			String type = record.get(2);
			String email = record.get(3);
			String creditCardNumber = record.get(4);
			String creditCardExpiration = record.get(5);
			String creditCardZip = record.get(6);
			String dueDateTime = record.get(7);

			if (request.getImdbId().equals(imdbId)) {
				email = request.getEmail();
				creditCardNumber = request.getCardNumber();
				creditCardExpiration = request.getCardExpiration();
				creditCardZip = request.getZip();
				dueDateTime = LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_DATE_TIME);
			}
			
			records.add(String.format("%s,%s,%s,%s,%s,%s,%s,%s", 
					imdbId,
					title,
					type,
					email,
					creditCardNumber,
					creditCardExpiration,
					creditCardZip,
					dueDateTime));
		}
		
		PrintWriter pw = new PrintWriter(new FileWriter(movieDb));
		for(String line : records) {
			pw.println(line);
		}
		pw.flush();
		pw.close();
		
		request.setApproved(Boolean.TRUE);
		exchange.getOut().setBody(gson.toJson(request));
	}
}
