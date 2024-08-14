package com.ads.robot.services;

import com.ads.robot.entities.Configuration;
import com.ads.robot.entities.TestCase;
import com.ads.robot.entities.TestSuite;
import com.ads.robot.repositories.TestSuiteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestSuiteService {

  @Autowired
  private TestSuiteRepository testSuiteRepository;
  @Autowired
  private TestCaseService testCaseService;
  @Autowired
  private GitService gitService;
  @Autowired
  private RobotFileGeneratorService robotFileGeneratorService;
  private String path;

  @Autowired
  TestSuiteService(ConfigurationService configurationService) {
    Configuration configuration = configurationService.getConfiguration();
    String url = configuration.getUrl();
    String projectName = configuration.getProjectName();
    String password = configuration.getPassword();
    String relativePath = "/" + projectName + "/test/";
    String homeDirectory = System.getProperty("user.home");
    path = homeDirectory + relativePath;
  }

  public List<TestSuite> getAllTestSuites() {
    return testSuiteRepository.findAll();
  }

  public TestSuite getTestSuite(int id) {
    return testSuiteRepository.findById(id).get();
  }

  public TestSuite addTestSuite(TestSuite testSuite) {
    return testSuiteRepository.save(testSuite);
  }

  public void updateTestSuite(TestSuite testSuite) {
    testSuiteRepository.save(testSuite);
  }

  public void deleteTestSuite(int id) {
    TestSuite testSuite = this.testSuiteRepository.findById(id).get();
    testSuiteRepository.deleteById(id);
    this.gitService.removeTestSuiteFromRemote(testSuite);
  }

  public TestCase addTestCase(TestCase testCase, int testSuiteId) {
    testCase.setTestSuite(getTestSuite(testSuiteId));
    return testCaseService.addTestCase(testCase);
  }

  public void updateTestSuiteFile(int testSuiteId) {
    robotFileGeneratorService.generateRobotFile(testSuiteId, path);
    gitService.updateRemote();
  }
}
