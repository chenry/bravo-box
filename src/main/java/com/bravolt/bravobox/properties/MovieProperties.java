package com.bravolt.bravobox.properties;

import java.io.IOException;
import java.util.Properties;

public class MovieProperties {
	
	private static final Properties properties = new Properties();
	
	static {
		try {
		properties.load(ClassLoader.getSystemResourceAsStream("movies.properties"));
		} catch(IOException ioE){}
	}
	
	public static final Object get(String key) {
		return MovieProperties.properties != null ? properties.get(key) : null;
	}

}
