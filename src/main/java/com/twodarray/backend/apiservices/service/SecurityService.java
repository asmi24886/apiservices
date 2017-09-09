package com.twodarray.backend.apiservices.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * The interface Security service.
 */
public interface SecurityService
{
	/**
	 * Has protected access boolean.
	 *
	 * @return the boolean
	 */
	public Boolean hasThirdLevelAccess();

	public Boolean hasSecondLevelAccess();

	public Boolean hasFirstLevelAccess();
}
