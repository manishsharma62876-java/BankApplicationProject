package com.manish.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.manish.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final JwtUtil jwtUtil;

	public AuthController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password) {

		if ("manish".equals(username) && "manish".equals(password)) {
			return jwtUtil.generateToken(username);
		} else {
			throw new RuntimeException("Invalid credentials");
		}
	}
}
