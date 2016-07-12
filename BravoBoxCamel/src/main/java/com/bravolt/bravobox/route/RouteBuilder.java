package com.bravolt.bravobox.route;

public class RouteBuilder extends org.apache.camel.builder.RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("properties:{{inbound.movie.information}}").bean();

	}

}
