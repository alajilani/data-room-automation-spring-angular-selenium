package com.ads.robot.repositories;

import com.ads.robot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role,Long>{
	public Role findByName(String name);

}
