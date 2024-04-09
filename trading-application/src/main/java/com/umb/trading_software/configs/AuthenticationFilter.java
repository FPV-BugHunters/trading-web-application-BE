package com.umb.trading_software.configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.umb.trading_software.DTO.AppUserDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	private final JwtProvider jwtService;

	public AuthenticationFilter(JwtProvider jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, java.io.IOException {

				
		// Get token from the Authorization header
		String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (jws != null) {

			AppUserDTO user = jwtService.getAuthUser(request);

			ArrayList<String> roles = user.getRoles();

			System.out.println("Roles: " + roles);

			List<GrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toList());

			System.out.println("Authorities: " + authorities);
			// Create an Authentication object
			// 
			//


			request.setAttribute("user", user);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
}