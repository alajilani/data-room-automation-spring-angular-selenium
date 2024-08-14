package com.ads.robot.repositories;

import
    com.ads.robot.entities.TestSuite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestSuiteRepository extends JpaRepository<TestSuite,Integer> {
    @Query("select ts from TestSuite ts ,TestCase tc where ?1 = tc.id and ts.id = tc.testSuite.id   ")
    public TestSuite findByTestcaseId(Integer testCaseId);
}

