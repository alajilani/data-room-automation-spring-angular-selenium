package com.ads.robot.controllers;

import com.ads.robot.dto.UserEdit;
import com.ads.robot.dto.UserDto;
import com.ads.robot.dto.converters.UserConverter;
import com.ads.robot.entities.User;
import com.ads.robot.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserConverter userConverter;

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/get/all")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> Users = userService.findAllUsers().stream().map(userConverter::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(Users, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<UserDto> addUser(@RequestBody User User) {
		UserDto newUser = userConverter.convertToDto(userService.addUser(User));
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserEdit user) {
		UserDto updatedUser = userConverter.convertToDto(userService.updateUser(user));
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

