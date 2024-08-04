package it.madefelicis.ticket_platform.response;

import org.springframework.http.HttpStatus;

public class Payload <T>  {
	
	private T payload;
	
	private String errorMessage;
	
	private HttpStatus status;

	
//	COSTRUTTORI
	
	public Payload() { }
	
	public Payload(T payload, String errorMessage, HttpStatus status) {
		this.payload = payload;
		this.errorMessage = errorMessage;
		status = status;
	}
	
	
//	GETTERs - SETTERs


	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
