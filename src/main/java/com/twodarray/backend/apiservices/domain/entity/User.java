package com.twodarray.backend.apiservices.domain.entity;

import com.twodarray.backend.apiservices.domain.base.DomainBase;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * The type User.
 */
public class User extends DomainBase
{
	@Id
	private String _id;

	private String fullName;
	private String username;
	private String password;
	private String email;
	private Date lastPasswordReset;
	private String authorities;

	/**
	 * Instantiates a new User.
	 */
	public User() {
		super();
	}

	/**
	 * Instantiates a new User.
	 *
	 * @param username          the username
	 * @param password          the password
	 * @param email             the email
	 * @param lastPasswordReset the last password reset
	 * @param authorities       the authorities
	 */
	public User(String fullName, String username, String password, String email, Date lastPasswordReset, String authorities) {
		this.setUsername(username);
		this.setFullName(fullName);
		this.setPassword(password);
		this.setEmail(email);
		this.setLastPasswordReset(lastPasswordReset);
		this.setAuthorities(authorities);
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this._id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(String id) {
		this._id = id;
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

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets last password reset.
	 *
	 * @return the last password reset
	 */
	public Date getLastPasswordReset() {
		return this.lastPasswordReset;
	}

	/**
	 * Sets last password reset.
	 *
	 * @param lastPasswordReset the last password reset
	 */
	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	/**
	 * Gets authorities.
	 *
	 * @return the authorities
	 */
	public String getAuthorities() {
		return this.authorities;
	}

	/**
	 * Sets authorities.
	 *
	 * @param authorities the authorities
	 */
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

}
