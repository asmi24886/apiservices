package com.twodarray.backend.apiservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * The type Api services application.
 */
@SpringBootApplication
public class ApiServicesApplication extends SpringBootServletInitializer
{
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(ApiServicesApplication.class, args);
	}

}
