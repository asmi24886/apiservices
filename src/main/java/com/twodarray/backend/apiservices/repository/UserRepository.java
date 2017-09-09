package com.twodarray.backend.apiservices.repository;

import com.twodarray.backend.apiservices.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	/**
	 * Find by username user.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User findByUsername(String username);
	User findByEmail(String email);
}
