package com.ads.robot.dto;

public class TestCaseCloneDto {

  Integer testSuiteId;
  Integer testCaseId;
  String testCaseName;

  public Integer getTestSuiteId() {
    return testSuiteId;
  }

  public void setTestSuiteId(Integer testSuiteId) {
    this.testSuiteId = testSuiteId;
  }

  public Integer getTestCaseId() {
    return testCaseId;
  }

  public void setTestCaseId(Integer testCaseId) {
    this.testCaseId = testCaseId;
  }

  public String getTestCaseName() {
    return testCaseName;
  }

  public void setTestCaseName(String testCaseName) {
    this.testCaseName = testCaseName;
  }
}
