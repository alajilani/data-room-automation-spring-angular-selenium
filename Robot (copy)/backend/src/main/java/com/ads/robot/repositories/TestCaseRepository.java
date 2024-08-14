package com.ads.robot.repositories;

import com.ads.robot.entities.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase,Integer> {
}
