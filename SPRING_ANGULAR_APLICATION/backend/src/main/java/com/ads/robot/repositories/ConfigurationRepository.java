package com.ads.robot.repositories;

import com.ads.robot.entities.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration,Integer> {

}