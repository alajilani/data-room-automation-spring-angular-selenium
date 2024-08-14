package com.ads.robot.services;

import com.ads.robot.entities.TestCase;
import com.ads.robot.repositories.TestCaseRepository;
import com.ads.robot.repositories.TestCaseRowRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class migration {

  @Autowired
  private TestCaseRepository testCaseRepository;
  @Autowired
  private TestCaseRowRepository testCaseRowRepository;

  @PostConstruct
  void migrate() {
    if (true) {
      int i = 0;
      List<TestCase> testCaseList = testCaseRepository.findAll();
      for (TestCase testCase : testCaseList) {
        testCaseRowRepository.deleteByIdNotInAndTestCaseIdEquals(testCase.getTestCaseRowOrder(),
            testCase.getId());
        i++;
        System.out.println(i + "/" + testCaseList.size());
      }
    }
    if (true) {
      int i = 0;
      List<TestCase> testCaseList = testCaseRepository.findAll();
      for (TestCase testCase : testCaseList) {
        if (!testCase.getTestCaseRows().stream().map(testCaseRow -> testCaseRow.getId()).toList()
            .containsAll(testCase.getTestCaseRowOrder())) {
          System.out.println("PROBLEM");
          System.out.println(testCase.getId());
        }
        i++;
        System.out.println(i + "/" + testCaseList.size());
      }
    }
  }
}
