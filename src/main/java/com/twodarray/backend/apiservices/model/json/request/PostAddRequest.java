package com.twodarray.backend.apiservices.model.json.request;

import com.twodarray.backend.apiservices.model.base.ModelBase;

public class PostAddRequest extends ModelBase
{
	private static final long serialVersionUID = 6624798680748515507L;
	private String userid;
	private String title;
	private String content;
	private String tags;

	public PostAddRequest()
	{
		super();
	}

	public PostAddRequest(String userid, String title, String content, String tags)
	{
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.tags = tags;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags(String tags)
	{
		this.tags = tags;
	}
}
