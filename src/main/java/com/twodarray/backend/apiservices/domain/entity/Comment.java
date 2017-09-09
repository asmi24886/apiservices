package com.twodarray.backend.apiservices.domain.entity;

import com.twodarray.backend.apiservices.domain.base.DomainBase;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class Comment extends DomainBase
{
	private String id;
	private User author;
	private String content;
	private String tags;
	private Date createDate;
	private Date lastModified;
	private String postId;

	public Comment()
	{
		super();
	}

	public Comment(User author, String content, String tags, Date createDate, Date lastModified, String postId)
	{
		this.author = author;
		this.content = content;
		this.tags = tags;
		this.createDate = createDate;
		this.lastModified = lastModified;
		this.postId = postId;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
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

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getLastModified()
	{
		return lastModified;
	}

	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
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
