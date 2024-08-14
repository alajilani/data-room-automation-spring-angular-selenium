package com.ads.robot.services;

import com.ads.robot.dto.JwtSignInRequest;
import com.ads.robot.entities.Role;
import com.ads.robot.entities.User;
import com.ads.robot.exceptions.UserNotFoundException;
import com.ads.robot.repositories.RoleRepo;
import com.ads.robot.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class AuthenticationService {
	
	private String PASSWORD_RESET_URL="http://localhost:4200/client/passwordreset/";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepository;

	
	private Role adminRole;
	private Role userRole;


	@Autowired
	public AuthenticationService(RoleRepo roleRepository) {
		adminRole=roleRepository.findByName(Role.ADMIN);
		userRole=roleRepository.findByName(Role.USER);

	}

	public void authenticate(JwtSignInRequest authRequest) throws Exception {
		Objects.requireNonNull(authRequest.getUsername());
		Objects.requireNonNull(authRequest.getPassword());

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}


	private User checkIfUserExists(String username) {
		User user =userRepository.findByUsername(username);
		if(user==null)
			throw new UserNotFoundException("User not found");
		return user;
	}
}
