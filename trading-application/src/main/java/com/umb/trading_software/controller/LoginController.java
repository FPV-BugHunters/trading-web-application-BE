package com.umb.trading_software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umb.trading_software.DTO.AccountCredentialsDTO;
import com.umb.trading_software.DTO.AppUserDTO;
import com.umb.trading_software.configs.JwtProvider;
import com.umb.trading_software.domain.AppUser;
import com.umb.trading_software.domain.AppUserRepository;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class LoginController {

	private final JwtProvider jwtService;
	private final AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


	@Autowired
	private AppUserRepository appUserRepository;


	public LoginController(JwtProvider jwtService, AuthenticationManager authenticationManager, AppUserRepository appUserRepository) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.appUserRepository = appUserRepository;
	}
	

	@PostMapping("/api/user/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentialsDTO credentials) {
		System.out.println( credentials)	;


		UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(credentials.getUsername(),
			credentials.getPassword());

		Authentication auth = authenticationManager.authenticate(creds);
		

		AppUserDTO user = new AppUserDTO();

		user.setUsername(credentials.getUsername());
		user.setPassword(credentials.getPassword());


		// Get the user from the database
		AppUser appUser = appUserRepository.findByUsername(credentials.getUsername()).get();
		

		// Get the roles of the user
		ArrayList<String> roles = new ArrayList<>();
		appUser.getRoles().forEach(role -> {
			roles.add(role.getName());
		});
		user.setRoles(roles);


		// Get the trading account ids of the user
		ArrayList<Integer> tradingAccountIds = new ArrayList<>();
		appUser.getCtraderAccounts().forEach(account -> {
			account.getTradingAccounts().forEach(tradingAccount -> {
				tradingAccountIds.add(tradingAccount.getCtidTraderAccountId());
			});
		});
		
		user.setTradingAccountIds(tradingAccountIds);
		
		
		// Generate token
		String jwts = jwtService.getToken(user);

		// Build response with the generated token
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
	}
}