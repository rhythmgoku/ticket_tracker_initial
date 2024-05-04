package com.greatlearning.tickettracker.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.tickettracker.security.entity.Users;
import com.greatlearning.tickettracker.security.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

	@Autowired
	UserRepository customUserRepository;

	private static Users loggedInUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users customUser = customUserRepository.findByUsername(username);
		if (customUser == null) {
			throw new UsernameNotFoundException("User Not Found");
		}
		this.loggedInUser = customUser;
		return customUser;
	}

	@Override
	public Users geLoggedInUser() {
		return this.loggedInUser;
	}

	@Override
	public void seLoggedInUser(Users users) {
		this.loggedInUser = users;
	}


}
