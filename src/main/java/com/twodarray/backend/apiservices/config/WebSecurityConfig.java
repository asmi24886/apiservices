package com.twodarray.backend.apiservices.config;

import com.twodarray.backend.apiservices.security.AuthenticationTokenFilter;
import com.twodarray.backend.apiservices.security.EntryPointUnauthorizedHandler;
import com.twodarray.backend.apiservices.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Web security config.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private EntryPointUnauthorizedHandler unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SecurityService securityService;

	@Value("${apiservices.route.authentication}")
	String authentication;

	@Value("${apiservices.route.user}")
	String newuser;

	/**
	 * Configure authentication.
	 *
	 * @param authenticationManagerBuilder the authentication manager builder
	 * @throws Exception the exception
	 */
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(this.userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	/**
	 * Password encoder password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Authentication token filter bean authentication token filter.
	 *
	 * @return the authentication token filter
	 * @throws Exception the exception
	 */
	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	/**
	 * Security service security service.
	 *
	 * @return the security service
	 */
	@Bean
	public SecurityService securityService() {
		return this.securityService;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(this.unauthorizedHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/"+newuser+"/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/"+authentication+"/**").permitAll()
				.anyRequest().authenticated();

		// Custom JWT based authentication
		httpSecurity
				.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

	}
}
