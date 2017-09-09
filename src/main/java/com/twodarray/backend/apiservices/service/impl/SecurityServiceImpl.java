package com.twodarray.backend.apiservices.service.impl;

import com.twodarray.backend.apiservices.service.SecurityService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The type Security service.
 */
@Service
public class SecurityServiceImpl implements SecurityService
{
	@Override
	public Boolean hasThirdLevelAccess()
	{
		return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")));
	}

	@Override
	public Boolean hasSecondLevelAccess()
	{
		if(hasThirdLevelAccess())
		{
			return true;
		}
		else
		{
			return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROOT")));
		}

	}

	@Override
	public Boolean hasFirstLevelAccess()
	{
		if(hasThirdLevelAccess() || hasSecondLevelAccess())
		{
			return true;
		}
		else
		{
			return (SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("USER")));
		}
	}
}
