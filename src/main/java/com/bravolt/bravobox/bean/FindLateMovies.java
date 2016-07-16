package com.bravolt.bravobox.bean;

import java.io.File;
import java.io.IOException;
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

import com.bravolt.bravobox.model.movie.LateEmail;
import com.bravolt.bravobox.properties.MovieProperties;

public class FindLateMovies {

	public void getLateMovies(Exchange exchange) throws IOException {
		CSVParser parser = CSVParser.parse(new File(String.format("%s/%s", MovieProperties.get("inventory.dir"),
				MovieProperties.get("inventory.fileName"))), StandardCharsets.US_ASCII, CSVFormat.DEFAULT);
		List<LateEmail> lateEmailList = new ArrayList<>();
		
		String date = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		Boolean isHeader = Boolean.TRUE;
		for (CSVRecord csvRecord : parser.getRecords()) {
			LocalDateTime returnDateTime;

			if (isHeader) {
				isHeader = Boolean.FALSE;
				continue;
			}

			if (csvRecord.size() >= 8 && !StringUtils.isEmpty(csvRecord.get(7))) {
				//returnDateTime = LocalDateTime.parse(date);
				returnDateTime = LocalDateTime.parse(csvRecord.get(7));
				if (returnDateTime.isBefore(LocalDateTime.now())) {
					LateEmail lateEmail = new LateEmail();

					lateEmail.setDateDue(returnDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
					lateEmail.setEmail(csvRecord.get(3));
					lateEmail.setTitle(csvRecord.get(1));

					lateEmailList.add(lateEmail);
				}
			}

		}

		exchange.getIn().setBody(lateEmailList);
	}
}
