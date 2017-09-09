package com.twodarray.backend.apiservices.service.impl;

import com.twodarray.backend.apiservices.domain.entity.User;
import com.twodarray.backend.apiservices.model.factory.TwoDArrayUserFactory;
import com.twodarray.backend.apiservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = this.userRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		} else {
			return TwoDArrayUserFactory.create(user);
		}

	}
}
