package com.bravolt.bravobox.bean;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

import com.bravolt.bravobox.model.movie.LateEmail;

public class SetupEmailProperties {

	public void setupEmailProperties(Exchange exchange) {
		LateEmail lateEmail = exchange.getIn().getBody(LateEmail.class);
		String email = String.format("The movie \"%s\" was due on %s. Please, return the movie as soon as possible so others may enjoy it too!" , lateEmail.getTitle(), lateEmail.getDateDue());
		Message message = exchange.getOut();
		
		message.setHeader("to", lateEmail.getEmail());
		message.setHeader("subject", String.format("BravoBox: %s is now late on being returned", lateEmail.getTitle()));
		
		exchange.getOut().setBody(email);
	}
}
