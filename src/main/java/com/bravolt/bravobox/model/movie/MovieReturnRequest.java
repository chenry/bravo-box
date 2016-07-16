package com.bravolt.bravobox.model.movie;

public class MovieReturnRequest {

	private String imdbId;
	private Boolean returned = Boolean.FALSE;

	public Boolean getReturned() {
		return returned;
	}

	public void setReturned(Boolean returned) {
		this.returned = returned;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}
}
