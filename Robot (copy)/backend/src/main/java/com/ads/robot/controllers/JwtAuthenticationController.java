package com.ads.robot.controllers;


import com.ads.robot.config.JwtTokenUtil;
import com.ads.robot.dto.JwtResponse;
import com.ads.robot.dto.JwtSignInRequest;
import com.ads.robot.dto.UserDto;
import com.ads.robot.dto.converters.UserConverter;
import com.ads.robot.entities.Role;
import com.ads.robot.entities.User;
import com.ads.robot.exceptions.UserNotFoundException;
import com.ads.robot.services.AuthenticationService;
import com.ads.robot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserConverter userConverter;


	@RequestMapping(value = "/auth/signin", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtSignInRequest authenticationRequest) throws Exception{

		authenticationService.authenticate(authenticationRequest);

		try {
			final User user = userService.findUserByUsername(authenticationRequest.getUsername());
			UserDto userDto = userConverter.convertToDto(user);
			final String token = jwtTokenUtil.generateToken(user);
			return new ResponseEntity<JwtResponse>(new JwtResponse(token,userDto), HttpStatus.OK);
		}catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@RequestMapping(value = "/auth/signin/admin", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> createAuthenticationTokenForAdmin(@RequestBody JwtSignInRequest authenticationRequest) throws Exception{

		authenticationService.authenticate(authenticationRequest);

		try {
			boolean isAdmin =this.userService.findUserByUsername(authenticationRequest.getUsername()).
					getRoles().stream().anyMatch((Role r)-> r.getName().equals(Role.ADMIN));
			if(!isAdmin)
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			return new ResponseEntity<JwtResponse>(new JwtResponse(token,null), HttpStatus.OK);
		}catch(UserNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}

}
