package com.twodarray.backend.apiservices.domain.entity;

import com.twodarray.backend.apiservices.domain.base.DomainBase;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post extends DomainBase
{
	@Id
	private String _id;

	private User author;
	private String title;
	private String content;
	private String tags;
	private Date createDate;
	private Date lastModified;
	private List<Comment> comments;

	public Post()
	{
		super();
	}

	public Post(User author, String title, String content, String tags, Date createDate, Date lastModified)
	{
		this.author = author;
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.createDate = createDate;
		this.lastModified = lastModified;
		this.comments = new ArrayList<>();
	}

	public String addComment(Comment comment)
	{
		if(this.comments == null)
		{
			this.comments = new ArrayList<>();
		}
		int size = this.comments.size();
		String id = this._id+"-"+size;
		comment.setId(id);

		if(comment != null)
		{
			this.comments.add(size, comment);
		}
		return id;
	}

	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
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
}
