package com.example.PROJECT1.DTO;

public class AuthenticationResponse {

	private String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public AuthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}
}
