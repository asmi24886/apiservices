package com.twodarray.backend.apiservices.domain.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * The type Domain base.
 */
public class DomainBase implements Serializable
{
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
