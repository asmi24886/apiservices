package com.twodarray.backend.apiservices.model.base;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * The type Model base.
 */
public class ModelBase implements Serializable
{
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
