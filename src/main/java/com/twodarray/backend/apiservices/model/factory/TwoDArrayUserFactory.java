package com.twodarray.backend.apiservices.model.factory;

import com.twodarray.backend.apiservices.domain.entity.User;
import com.twodarray.backend.apiservices.model.security.TwoDArrayUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * The type Two d array user factory.
 */
public class TwoDArrayUserFactory
{
	/**
	 * Create two d array user.
	 *
	 * @param user the user
	 * @return the two d array user
	 */
	public static TwoDArrayUser create(User user) {
		Collection<? extends GrantedAuthority> authorities;
		try {
			authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
		} catch (Exception e) {
			authorities = null;
		}
		return new TwoDArrayUser(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getLastPasswordReset(),
				authorities
		);
	}
}
