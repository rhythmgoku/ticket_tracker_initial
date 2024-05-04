package com.greatlearning.tickettracker.security.util;

import java.util.Map;

import com.greatlearning.tickettracker.security.entity.Users;

public class UserUtil {

	public static String getHighestRole(Users user, Map<Integer, String> roleMap) {
		return roleMap.entrySet().stream().filter(
				entry -> user.getRoles().stream().map(role -> role.getName()).toList().contains(entry.getValue()))
				.min(Map.Entry.comparingByKey()).get().getValue();
	}

}
