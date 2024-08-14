package com.ads.robot.repositories;

import com.ads.robot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	public User findByUsername(String username);
}
