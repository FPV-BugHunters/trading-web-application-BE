package com.umb.trading_software.configs;

import org.springframework.stereotype.Component;

import com.umb.trading_software.DTO.AppUserDTO;
import com.umb.trading_software.domain.AppUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	static final String PREFIX = "Bearer ";

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expirationtime}")
	public long EXPIRATIONTIME;

	// 1 day in ms. Should be shorter in production.

	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String getToken(AppUserDTO appUserDTO) {

		// Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(appUserDTO.getRoles());
		ArrayList<String> roles = appUserDTO.getRoles();
		ArrayList<Integer> tradingAccountIds = appUserDTO.getTradingAccountIds();

		String token = Jwts.builder()
				.setSubject(appUserDTO.getUsername())
				.claim("roles", roles)
				.claim("tradingAccountIds", tradingAccountIds)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(key)
				.compact();

		return token;
	}


	public AppUserDTO getAuthUser(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (token != null) {

			JwtParserBuilder jwtParser = Jwts.parserBuilder().setSigningKey(key);
			Jws<Claims> claimsJws = jwtParser.build().parseClaimsJws(token.replace(PREFIX, ""));
			Claims claims = claimsJws.getBody();
			
			// Get roles
			ArrayList<String> roles = new ArrayList<>();
			if (claims.get("roles") != null) {
				roles = claims.get("roles", ArrayList.class);
			}

			// Get trading account ids
			ArrayList<Integer> tradingAccountIds = new ArrayList<>();
			if (claims.get("tradingAccountIds") != null) {
				tradingAccountIds = (ArrayList<Integer>) claims.get("tradingAccountIds");
			}

			AppUserDTO user = new AppUserDTO();
			user.setUsername(claims.getSubject());
			user.setTradingAccountIds(tradingAccountIds);
			user.setRoles(roles);	

			return user;
		}
		return null;
	}
}