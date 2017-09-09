package com.twodarray.backend.apiservices.model.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * The type Two d array user.
 */
public class TwoDArrayUser implements UserDetails
{
	private String id;
	private String username;
	private String password;
	private String email;
	private Date lastPasswordReset;
	private Collection<? extends GrantedAuthority> authorities;
	private Boolean accountNonExpired = true;
	private Boolean accountNonLocked = true;
	private Boolean credentialsNonExpired = true;
	private Boolean enabled = true;

	/**
	 * Instantiates a new Two d array user.
	 */
	public TwoDArrayUser() {
		super();
	}

	/**
	 * Instantiates a new Two d array user.
	 *
	 * @param id                the id
	 * @param username          the username
	 * @param password          the password
	 * @param email             the email
	 * @param lastPasswordReset the last password reset
	 * @param authorities       the authorities
	 */
	public TwoDArrayUser(String id, String username, String password, String email, Date lastPasswordReset, Collection<? extends GrantedAuthority> authorities) {
		this.setId(id);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setLastPasswordReset(lastPasswordReset);
		this.setAuthorities(authorities);
	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(String  id) {
		this.id = id;
	}

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

	@JsonIgnore
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
	@JsonIgnore
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	/**
	 * Sets authorities.
	 *
	 * @param authorities the authorities
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * Gets account non expired.
	 *
	 * @return the account non expired
	 */
	@JsonIgnore
	public Boolean getAccountNonExpired() {
		return this.accountNonExpired;
	}

	/**
	 * Sets account non expired.
	 *
	 * @param accountNonExpired the account non expired
	 */
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.getAccountNonExpired();
	}

	/**
	 * Gets account non locked.
	 *
	 * @return the account non locked
	 */
	@JsonIgnore
	public Boolean getAccountNonLocked() {
		return this.accountNonLocked;
	}

	/**
	 * Sets account non locked.
	 *
	 * @param accountNonLocked the account non locked
	 */
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.getAccountNonLocked();
	}

	/**
	 * Gets credentials non expired.
	 *
	 * @return the credentials non expired
	 */
	@JsonIgnore
	public Boolean getCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	/**
	 * Sets credentials non expired.
	 *
	 * @param credentialsNonExpired the credentials non expired
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.getCredentialsNonExpired();
	}

	/**
	 * Gets enabled.
	 *
	 * @return the enabled
	 */
	@JsonIgnore
	public Boolean getEnabled() {
		return this.enabled;
	}

	/**
	 * Sets enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return this.getEnabled();
	}
}
