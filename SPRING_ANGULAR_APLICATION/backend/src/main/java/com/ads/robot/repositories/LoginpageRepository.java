package com.ads.robot.repositories;

import com.ads.robot.entities.Loginpage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginpageRepository extends JpaRepository<Loginpage,Long> {

    public Loginpage findById(long id);



}
