package org.stasnizhnichenko.test.jooby.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Response {
	
	@JsonProperty(value = "text", required = true)
	private String text;
	@JsonProperty(value = "end_session", required = true, defaultValue = "true")
	private Boolean end_session;

	public Response() {
		
	}
	
	public Response(String t, Boolean b) {
		this.text = t;
		this.end_session = b;
	}
	
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getEnd_session() {
		return this.end_session;
	}
	public void setEnd_session(Boolean end_session) {
		this.end_session = end_session;
	}
	
		
}
