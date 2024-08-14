package com.ads.robot.services;

import com.ads.robot.dto.UserEdit;
import com.ads.robot.dto.converters.UserConverter;
import com.ads.robot.entities.User;
import com.ads.robot.exceptions.UserNotFoundException;
import com.ads.robot.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserConverter userConverter;
	
	public List<User> findAllUsers(){
		return this.userRepo.findAll();
	}
	public User findUserById(Long id) throws UserNotFoundException {
		return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
	}
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public User addUser(User user) {
		String encodedPassword= passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepo.save(user);	}
	
	public User updateUser(UserEdit userEdit) throws IllegalArgumentException{
		User registredUser = findUserById(userEdit.getUser().getId());
		User user = userEdit.getUser();
		if(userEdit.isUpdatePassword()){
			user.setPassword(passwordEncoder.encode(userEdit.getUser().getPassword()));
		}else{
			user.setPassword(registredUser.getPassword());
		}
		return userRepo.save(user);
	}
	public void deleteUser(Long id) throws IllegalArgumentException{
		userRepo.deleteById(id);
    }
}
