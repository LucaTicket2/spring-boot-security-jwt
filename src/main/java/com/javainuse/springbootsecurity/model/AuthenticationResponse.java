package com.javainuse.springbootsecurity.model;

/**
 * The type Authentication response.
 */
public class AuthenticationResponse {

	private String token;

	/**
	 * Instantiates a new Authentication response.
	 */
	public AuthenticationResponse() {

	}

	/**
	 * Instantiates a new Authentication response.
	 *
	 * @param token the token
	 */
	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	/**
	 * Gets token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets token.
	 *
	 * @param token the token
	 */
	public void setToken(String token) {
		this.token = token;
	}


}
