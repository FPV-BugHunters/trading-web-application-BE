package com.umb.trading_software.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umb.trading_software.domain.AppUser;
import com.umb.trading_software.domain.AppUserRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final AppUserRepository repository;

	public UserDetailsServiceImpl(AppUserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AppUser> user = repository.findByUsername(username);
		UserBuilder builder = null;
		if (user.isPresent()) {

			AppUser currentUser = user.get();
			
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(currentUser.getPassword());

			// List<GrantedAuthority> authorities = currentUser.getRoles().stream()
			// 		.map(role -> new SimpleGrantedAuthority(role.getName()))
			// 		.collect(Collectors.toList());
			// System.out.println("Authorities: " + authorities);
			
			// builder.authorities(authorities);
			//
			// builder.roles(authorities.stream().map(GrantedAuthority::getAuthority).toArray(String[]::new));
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
		return builder.build();
	}
}