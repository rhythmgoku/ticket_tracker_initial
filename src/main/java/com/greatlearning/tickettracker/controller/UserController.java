package com.greatlearning.tickettracker.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.tickettracker.security.entity.Users;
import com.greatlearning.tickettracker.security.service.CustomUserDetailsService;
import com.greatlearning.tickettracker.security.util.UserUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UserController {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	Map<Integer, String> roleMap;

	private String role;

	private String userName;

	@RequestMapping(value = { "/", "/home" })
	public String index(Model model) {

		Users user = customUserDetailsService.geLoggedInUser();

		model.addAttribute("username", user.getUsername().toUpperCase());
		String highestRole = findHighestRole(user);
		model.addAttribute("role", highestRole);

		userName = user.getUsername();
		role = highestRole;

		return "home";
	}

	private String findHighestRole(Users user) {
		return UserUtil.getHighestRole(user, roleMap);
	}

	@RequestMapping(value = { "/accessdenied" })
	public String accessDenied(Model model) {
		model.addAttribute("errorCode", "403");
		model.addAttribute("errorUniqueMessage", "You don't have enough privileges to perform this action");
		return "error";
	}

	@RequestMapping(value = { "/logon" })
	public String logon(Model model) {
		return "logon";
	}

	@RequestMapping(value = { "/redirect-to-base" })
	public String redirectToBase() {
		return "redirect:/" + role.toLowerCase() + "/tickets";
	}

	@GetMapping("/exit")
	public String logout(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			customUserDetailsService.seLoggedInUser(null);
		}
		return "exit";
	}

}
