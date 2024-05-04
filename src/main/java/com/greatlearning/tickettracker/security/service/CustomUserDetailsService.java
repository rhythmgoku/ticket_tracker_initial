package com.greatlearning.tickettracker.security.service;

import com.greatlearning.tickettracker.security.entity.Users;

public interface CustomUserDetailsService {
	
	Users geLoggedInUser();
	
	void seLoggedInUser(Users users);


}
