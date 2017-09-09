package com.twodarray.backend.apiservices.model.json.response;

import com.twodarray.backend.apiservices.model.base.ModelBase;

public class GeneralResponse extends ModelBase
{
	private static final long serialVersionUID = -6693626180748515507L;
	private String message;

	public GeneralResponse()
	{
		super();
	}

	public GeneralResponse(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
