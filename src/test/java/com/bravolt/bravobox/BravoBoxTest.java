package com.bravolt.bravobox;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.bravolt.bravobox.model.movie.MovieListRequest;
import com.bravolt.bravobox.model.movie.MovieListResponse;
import com.bravolt.bravobox.model.movie.MovieRentalRequest;
import com.bravolt.bravobox.model.movie.MovieRequest;
import com.bravolt.bravobox.model.movie.MovieReturnRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BravoBoxTest extends CamelSpringTestSupport {
	
	private static final String CONTEXT = "META-INF/spring/camel-context.xml";
	private static final String ENDPOINT_LIST = "properties:inbound.movie.list";
	private static final String ENDPOINT_INFORMATION = "properties:inbound.movie.information";
	private static final String ENDPOINT_LATE = "properties:inbound.notify.late";
	private static final String ENDPOINT_RENT = "properties:inbound.rent";
	private static final String ENDPOINT_RETURN = "properties:inbound.return";

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void testMovieInformation() {
		Gson gson = new Gson();
		MovieRequest request = new MovieRequest();
		String requestJson;
		String responseJson;
		
		request.setImdbMovie("tt0086567");
		requestJson = gson.toJson(request);
	
		responseJson = template.requestBody(ENDPOINT_INFORMATION, requestJson, String.class);
		
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(responseJson).getAsJsonObject();
		
		assertFalse(StringUtils.isEmpty(responseJson));
	}
	
	@Test
	public void testMovieListTemplate_All() {
		String responseJson;
		Gson gson = new Gson();
		MovieListRequest request = new MovieListRequest();
		String requestJson;
		MovieListResponse[] responses = new MovieListResponse[1];
		
		requestJson = gson.toJson(request);
		
		responseJson = template.requestBody(ENDPOINT_LIST, requestJson, String.class);
		responses = gson.fromJson(responseJson, responses.getClass());
	
		assertFalse(StringUtils.isEmpty(responseJson));
		assertEquals(12, responses.length);
	}
	
	@Test
	public void testMovieListTemplate_Thriller() {
		String responseJson;
		Gson gson = new Gson();
		MovieListRequest request = new MovieListRequest();
		String requestJson;
		MovieListResponse[] responses = new MovieListResponse[1];
		String type = "Thriller";
		
		request.setType(type);
		requestJson = gson.toJson(request);
		
		responseJson = template.requestBody(ENDPOINT_LIST, requestJson, String.class);
		responses = gson.fromJson(responseJson, responses.getClass());
	
		assertFalse(StringUtils.isEmpty(responseJson));
		for(MovieListResponse response : responses) {
			List<String> types = Arrays.asList(response.getType().split(";"));
		
			assertTrue(types.contains(type));
		}
		assertEquals(4, responses.length);
	}

	@Test
	public void testMovieListTemplate_Drama() {
		String responseJson;
		Gson gson = new Gson();
		MovieListRequest request = new MovieListRequest();
		String requestJson;
		MovieListResponse[] responses = new MovieListResponse[1];
		String type = "Drama";
		
		request.setType(type);
		requestJson = gson.toJson(request);
		
		responseJson = template.requestBody(ENDPOINT_LIST, requestJson, String.class);
		responses = gson.fromJson(responseJson, responses.getClass());
	
		assertFalse(StringUtils.isEmpty(responseJson));
		for(MovieListResponse response : responses) {
			List<String> types = Arrays.asList(response.getType().split(";"));
		
			assertTrue(types.contains(type));
		}
		assertEquals(5, responses.length);
	}
	
	@Test
	public void testMovieLateNotify() {
		template.requestBody(ENDPOINT_LATE, new Object());
	}
	
	@Test
	public void testMovieRent() {
		Gson gson = new Gson();
		MovieRentalRequest request = new MovieRentalRequest();
		MovieRentalRequest response;
		
		request.setImdbId("tt0086567");
		request.setCardExpiration("0718");
		request.setEmail("dustin.clifford@gmail.com");
		request.setZip("49428");
		
		response = gson.fromJson(template.requestBody(ENDPOINT_RENT, gson.toJson(request), String.class), MovieRentalRequest.class);
		
		assertTrue(response.getApproved());
	}
	
	@Test
	public void testMovieReturn() {
		Gson gson = new Gson();
		MovieReturnRequest request = new MovieReturnRequest();
		MovieReturnRequest response;
		
		request.setImdbId("tt0086567");
		
		response = gson.fromJson(template.requestBody(ENDPOINT_RETURN, gson.toJson(request), String.class), MovieReturnRequest.class);
		
		assertTrue(response.getReturned());
	}

	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(CONTEXT);
	}
}