package com.ads.robot.dto;

public class JwtResponse {

	private final String jwttoken;
	private final UserDto user;

	public JwtResponse(String jwttoken, UserDto user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public UserDto getUser() {
		return user;
	}
}