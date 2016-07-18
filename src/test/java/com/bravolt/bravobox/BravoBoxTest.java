package com.bravolt.bravobox;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BravoBoxTest extends CamelSpringTestSupport {
	
	private static final String CONTEXT = "META-INF/spring/camel-context.xml";
	private static final String ENDPOINT_LIST = "properties:inbound.movie.list";
	private static final String ENDPOINT_INFORMATION = "properties:inbound.movie.information";
	private static final String ENDPOINT_RENT = "properties:inbound.rent";

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}
	
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(CONTEXT);
	}
}