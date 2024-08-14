package com.ads.robot.repositories;

import com.ads.robot.entities.FunctionRobot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunctionRepository extends JpaRepository<FunctionRobot,Integer> {
    public FunctionRobot findByName(String name);
}
