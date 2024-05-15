package com.rms.models;

import com.rms.payload.UserDto;

public class AuthenticationResponse {
	private String token;
	private String message;
	

	public AuthenticationResponse() {

	}

	public AuthenticationResponse(String token, String message) {
		super();
		this.token = token;
		this.message = message;
		
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}