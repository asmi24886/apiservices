package com.twodarray.backend.apiservices.security;

import com.twodarray.backend.apiservices.domain.entity.User;
import com.twodarray.backend.apiservices.model.security.TwoDArrayUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Token utils.
 */
@Component
public class TokenUtils
{
	private final Logger logger = Logger.getLogger(this.getClass());

	private final String AUDIENCE_UNKNOWN   = "unknown";
	private final String AUDIENCE_WEB       = "web";
	private final String AUDIENCE_MOBILE    = "mobile";
	private final String AUDIENCE_TABLET    = "tablet";

	@Value("${apiservices.token.secret}")
	private String secret;

	@Value("${apiservices.token.expiration}")
	private Long expiration;

	/**
	 * Gets username from token.
	 *
	 * @param token the token
	 * @return the username from token
	 */
	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * Gets created date from token.
	 *
	 * @param token the token
	 * @return the created date from token
	 */
	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	/**
	 * Gets expiration date from token.
	 *
	 * @param token the token
	 * @return the expiration date from token
	 */
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	/**
	 * Gets audience from token.
	 *
	 * @param token the token
	 * @return the audience from token
	 */
	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			audience = (String) claims.get("audience");
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(this.secret.getBytes("UTF-8"))
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + this.expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(this.generateCurrentDate());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private String generateAudience(Device device) {
		String audience = this.AUDIENCE_UNKNOWN;
		if (device.isNormal()) {
			audience = this.AUDIENCE_WEB;
		} else if (device.isTablet()) {
			audience = AUDIENCE_TABLET;
		} else if (device.isMobile()) {
			audience = AUDIENCE_MOBILE;
		}
		return audience;
	}

	private Boolean ignoreTokenExpiration(String token) {
		String audience = this.getAudienceFromToken(token);
		return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
	}

	/**
	 * Generate token string.
	 *
	 * @param userDetails the user details
	 * @param device      the device
	 * @return the string
	 */
	public String generateToken(UserDetails userDetails, Device device) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("sub", userDetails.getUsername());
		claims.put("audience", this.generateAudience(device));
		claims.put("created", this.generateCurrentDate());
		return this.generateToken(claims);
	}

	private String generateToken(Map<String, Object> claims) {
		try {
			return Jwts.builder()
					.setClaims(claims)
					.setExpiration(this.generateExpirationDate())
					.signWith(SignatureAlgorithm.HS512, this.secret.getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException ex) {
			//didn't want to have this method throw the exception, would rather log it and sign the token like it was before
			logger.warn(ex.getMessage());
			return Jwts.builder()
					.setClaims(claims)
					.setExpiration(this.generateExpirationDate())
					.signWith(SignatureAlgorithm.HS512, this.secret)
					.compact();
		}
	}

	/**
	 * Can token be refreshed boolean.
	 *
	 * @param token             the token
	 * @param lastPasswordReset the last password reset
	 * @return the boolean
	 */
	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = this.getCreatedDateFromToken(token);
		return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset)) && (!(this.isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
	}

	/**
	 * Refresh token string.
	 *
	 * @param token the token
	 * @return the string
	 */
	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			claims.put("created", this.generateCurrentDate());
			refreshedToken = this.generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	/**
	 * Validate token boolean.
	 *
	 * @param token       the token
	 * @param userDetails the user details
	 * @return the boolean
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		User user = convert(userDetails);
		final String username = this.getUsernameFromToken(token);
		final Date created = this.getCreatedDateFromToken(token);
		final Date expiration = this.getExpirationDateFromToken(token);
		return (username.equals(user.getUsername()) && !(this.isTokenExpired(token)) && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
	}

	/**
	 * Convert user.
	 *
	 * @param twoDArrayUser the two d array user
	 * @return the user
	 */
	public User convert(UserDetails twoDArrayUser)
	{
		User user = new User();
		user.setPassword(twoDArrayUser.getPassword());
		user.setAuthorities(twoDArrayUser.getAuthorities().toString());
		user.setUsername(twoDArrayUser.getUsername());
		return user;
	}

}
