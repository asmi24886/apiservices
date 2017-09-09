package com.twodarray.backend.apiservices.model.json.request;

import com.twodarray.backend.apiservices.domain.entity.User;
import com.twodarray.backend.apiservices.model.base.ModelBase;

import java.util.Date;

public class CommentAddRequest extends ModelBase
{
	private String username;
	private String content;
	private String tags;
	private String postId;

	public CommentAddRequest()
	{
		super();
	}

	public CommentAddRequest(String username, String content, String tags, String postId)
	{
		this.username = username;
		this.content = content;
		this.tags = tags;
		this.postId = postId;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
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

	public String getPostId()
	{
		return postId;
	}

	public void setPostId(String postId)
	{
		this.postId = postId;
	}
}

