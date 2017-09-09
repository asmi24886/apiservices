package com.twodarray.backend.apiservices.controller;

import com.twodarray.backend.apiservices.domain.entity.User;
import com.twodarray.backend.apiservices.model.json.request.UserAddRequest;
import com.twodarray.backend.apiservices.model.json.response.GeneralResponse;
import com.twodarray.backend.apiservices.model.json.response.UserResponse;
import com.twodarray.backend.apiservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("${apiservices.route.user}")
public class UserController
{

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


	@RequestMapping(
			method = RequestMethod.GET,
			value = "test"
	)
	//@PreAuthorize("hasAuthority('ADMIN')")
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("Hello World !!!");
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserAddRequest request)
	{
		// Check NULL
		if(request == null || request.getUsername() == null || request.getUsername().trim().equals("")
				|| request.getPassword() ==  null || request.getPassword().trim().equals("")
				|| request.getEmail() == null || request.getEmail().trim().equals(""))
		{
			return nullResponse();
		}

		// Validate username and email
		User user = userRepository.findByUsername(request.getUsername());
		if(user != null)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponse("Username already exists"));
		}

		user = userRepository.findByEmail(request.getEmail());
		if(user != null)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new GeneralResponse("Email ID already exists"));
		}

		// add
		user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setEmail(request.getEmail());
		user.setFullName(request.getFullname());
		user.setAuthorities("USER");
		userRepository.save(user);
		return ResponseEntity.ok(new GeneralResponse("User added"));
	}

	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("@securityService.hasFirstLevelAccess()")
	public ResponseEntity<?> findUser(@Param("username") String username)
	{
		if(username == null)
		{
			return nullResponse();
		}

		User user = userRepository.findByUsername(username);
		if(user == null)
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			UserResponse response = new UserResponse();
			response.setId(user.getId());
			response.setFullname(user.getFullName());
			response.setUsername(user.getUsername());
			response.setEmail(user.getEmail());
			response.setAuthorities(user.getAuthorities());
			return ResponseEntity.ok(response);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "list")
	@PreAuthorize("@securityService.hasThirdLevelAccess()")
	public ResponseEntity<?> findAllUsers()
	{
		return ResponseEntity.ok(userRepository.findAll());
	}

	private ResponseEntity<?> nullResponse()
	{
		return ResponseEntity.badRequest().body(new GeneralResponse("Request parameters can not be null"));
	}
}
