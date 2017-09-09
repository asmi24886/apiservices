package com.twodarray.backend.apiservices.model.json.request;

import com.twodarray.backend.apiservices.model.base.ModelBase;

/**
 * The type Authentication request.
 */
public class AuthenticationRequest extends ModelBase
{
	private static final long serialVersionUID = 6624726180748515507L;
	private String username;
	private String password;

	/**
	 * Instantiates a new Authentication request.
	 */
	public AuthenticationRequest() {
		super();
	}

	/**
	 * Instantiates a new Authentication request.
	 *
	 * @param username the username
	 * @param password the password
	 */
	public AuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
