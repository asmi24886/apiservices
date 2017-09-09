package com.twodarray.backend.apiservices.model.json.response;

import com.twodarray.backend.apiservices.model.base.ModelBase;

/**
 * The type Authentication response.
 */
public class AuthenticationResponse extends ModelBase
{
	private static final long serialVersionUID = -6624726180748515507L;
	private String token;

	/**
	 * Instantiates a new Authentication response.
	 */
	public AuthenticationResponse() {
		super();
	}

	/**
	 * Instantiates a new Authentication response.
	 *
	 * @param token the token
	 */
	public AuthenticationResponse(String token) {
		this.setToken(token);
	}

	/**
	 * Gets token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return this.token;
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
