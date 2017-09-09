package com.twodarray.backend.apiservices.model.json.response;

import com.twodarray.backend.apiservices.model.base.ModelBase;
import com.twodarray.backend.apiservices.repository.UserRepository;

public class UserResponse extends ModelBase
{
	private static final long serialVersionUID = -6693626180748515507L;
	private String id;
	private String fullname;
	private String username;
	private String email;
	private String authorities;

	public UserResponse()
	{
		super();
	}

	public UserResponse(String id, String fullname, String username, String email, String authorities)
	{
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.authorities = authorities;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getFullname()
	{
		return fullname;
	}

	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getAuthorities()
	{
		return authorities;
	}

	public void setAuthorities(String authorities)
	{
		this.authorities = authorities;
	}
}
