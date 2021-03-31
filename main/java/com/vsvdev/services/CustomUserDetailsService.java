package com.vsvdev.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vsvdev.entity.User;
import com.vsvdev.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	@Autowired
	public CustomUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = repository.findUserByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
		return build(user);
	}

	public User loadUserById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public static User build(User user) {
		List<SimpleGrantedAuthority> authorities = user.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
		return new User(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
	}
}
