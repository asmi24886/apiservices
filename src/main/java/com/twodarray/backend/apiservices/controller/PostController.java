package com.twodarray.backend.apiservices.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.twodarray.backend.apiservices.domain.entity.Comment;
import com.twodarray.backend.apiservices.domain.entity.Post;
import com.twodarray.backend.apiservices.domain.entity.User;
import com.twodarray.backend.apiservices.model.json.request.CommentAddRequest;
import com.twodarray.backend.apiservices.model.json.request.PostAddRequest;
import com.twodarray.backend.apiservices.model.json.response.GeneralResponse;
import com.twodarray.backend.apiservices.repository.PostRepository;
import com.twodarray.backend.apiservices.repository.UserRepository;
import com.twodarray.backend.apiservices.security.EntryPointUnauthorizedHandler;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * The type Protected controller.
 */
@RestController
@RequestMapping("${apiservices.route.post}")
public class PostController
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@RequestMapping(method = RequestMethod.GET, value = "test")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("Hello World !!!");
	}

	@RequestMapping(method = RequestMethod.GET, value = "list")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> findAllPosts()
	{
		return ResponseEntity.ok(postRepository.findAll());
	}

	@RequestMapping(method = RequestMethod.GET, value = "searchPost")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> findPost(@Param("postId") String postId)
	{
		if(postId == null || postId.trim().equals(""))
		{
			return nullResponse();
		}

		Post post = postRepository.findOne(postId);
		return ResponseEntity.ok(post);
	}

	@RequestMapping(method = RequestMethod.GET, value = "comment")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> findComment(@Param("commentId") String commentId)
	{
		if(commentId == null || commentId.trim().equals(""))
		{
			return nullResponse();
		}
		else
		{
			String[] parts = commentId.split("-");
			Post post = postRepository.findOne(parts[0].trim());
			Comment comment = post.getComments().get(Integer.parseInt(parts[1].trim()));
			return ResponseEntity.ok(comment);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> createPost(@RequestBody PostAddRequest request)
	{
		// Null Check
		if(request == null || request.getUserid() == null || request.getUserid().trim().equals("")
				|| request.getContent() == null || request.getContent().trim().equals("") ||
				request.getTitle() == null || request.getTitle().trim().equals(""))
		{
			return nullResponse();
		}

		// Username Validation and retrieve
		User user = userRepository.findOne(request.getUserid());
		if(user == null)
		{
			return ResponseEntity.unprocessableEntity().body(new GeneralResponse("Internal Error"));
		}

		// Prepare POST object
		Post post = new Post();
		post.setAuthor(user);
		post.setContent(request.getContent());
		post.setTitle(request.getTitle());
		post.setTags(request.getTags());
		post.setCreateDate(new Date());
		post.setLastModified(new Date());

		// Add Post
		postRepository.save(post);
		return ResponseEntity.ok(new GeneralResponse("Post added"));
	}

	@RequestMapping(method = RequestMethod.POST, value = "comment")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> createComment(@RequestBody CommentAddRequest request)
	{
		if(request == null || request.getUsername() == null || request.getUsername().trim().equals("")
				|| request.getContent() == null || request.getContent().trim().equals("")
				|| request.getPostId() == null || request.getPostId().trim().equals(""))
		{
			return nullResponse();
		}

		// find User
		User user = userRepository.findByUsername(request.getUsername());

		// find Post
		Post post = postRepository.findOne(request.getPostId());

		// Create Comment object
		Comment comment = new Comment();
		comment.setAuthor(user);
		comment.setContent(request.getContent());
		comment.setPostId(request.getPostId());
		comment.setTags(request.getTags());
		comment.setCreateDate(new Date());
		comment.setLastModified(new Date());

		// Add to the post
		post.addComment(comment);
		postRepository.save(post);
		return ResponseEntity.ok(new GeneralResponse("Comment Added"));
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{postId}")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> editPost(@PathVariable("postId") String postId,
			@RequestBody PostAddRequest request)
	{
		// Null Check
		if(request == null || request.getUserid() == null || request.getUserid().trim().equals("")
				|| request.getContent() == null || request.getContent().trim().equals("") ||
				request.getTitle() == null || request.getTitle().trim().equals(""))
		{
			return nullResponse();
		}

		// Check if the user is same
		Post post = postRepository.findOne(postId);
		if(!post.getAuthor().getId().equals(request.getUserid()))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
		}

		post.setLastModified(new Date());
		post.setTags(request.getTags());
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());

		postRepository.save(post);
		return ResponseEntity.ok(request);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "comment/{commentId}")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> editComment(@PathVariable("commentId") String commentId,
			@RequestBody CommentAddRequest request)
	{
		// Null Check
		if(request == null || request.getUsername() == null || request.getUsername().trim().equals("")
				|| request.getContent() == null || request.getContent().trim().equals("") ||
				request.getPostId() == null || request.getPostId().trim().equals("")
				|| commentId == null || commentId.trim().equals(""))
		{
			return nullResponse();
		}

		// Check Username
		Post post = postRepository.findOne(request.getPostId());
		String commentPos = commentId.split("-")[1].trim();
		Comment comment = post.getComments().get(Integer.parseInt(commentPos.trim()));
		if(!comment.getAuthor().getUsername().equals(request.getUsername()))
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
		}
		else
		{
			post.getComments().get(Integer.parseInt(commentPos.trim())).setLastModified(new Date());
			post.getComments().get(Integer.parseInt(commentPos.trim())).setContent(request.getContent());
			post.getComments().get(Integer.parseInt(commentPos.trim())).setTags(request.getTags());
			postRepository.save(post);
			return ResponseEntity.ok(post.getComments().get(Integer.parseInt(commentPos.trim())));
		}
	}

	private ResponseEntity<?> nullResponse()
	{
		return ResponseEntity.badRequest().body(new GeneralResponse("Request parameters can not be null"));
	}
}
