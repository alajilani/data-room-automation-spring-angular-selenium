package com.ads.robot.services;

import com.ads.robot.Utils.FileManager;
import com.ads.robot.dto.TestCaseCloneDto;
import com.ads.robot.dto.TestCaseDto;
import com.ads.robot.dto.TestCaseRowUpdateDto;
import com.ads.robot.dto.TestCaseUpdateDto;
import com.ads.robot.dto.converters.TestCaseConverter;
import com.ads.robot.dto.converters.TestCaseRowConverter;
import com.ads.robot.entities.Configuration;
import com.ads.robot.entities.TestCase;
import com.ads.robot.entities.TestCaseRow;
import com.ads.robot.entities.TestSuite;
import com.ads.robot.repositories.TestCaseRepository;
import com.ads.robot.repositories.TestCaseRowRepository;
import com.ads.robot.repositories.TestSuiteRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
@Service
public class TestCaseService {


  private String DELETED_STRING_REGEX = """
      #top-right-header {
          position: fixed;
          top: 0;
          right: 0;
          z-index: 1000;
          width: 12em;
          text-align: center;
      }""";
  private String REPLACEMENT_STRING = """
      #top-right-header {
          display: none;
      }""";

  private String url;
  private String projectName;
  private String password;
  private String relativePath;
  private String homeDirectory;
  private String path;
  private String projectPath;
  private String libPath;
  @Autowired
  private TestCaseRepository testCaseRepository;
  @Autowired
  private TestCaseRowRepository testCaseRowRepository;
  @Autowired
  private TestCaseConverter testCaseConverter;
  @Autowired
  private TestCaseRowConverter testCaseRowConverter;
  @Autowired
  private TestSuiteRepository testSuiteRepository;

  @Autowired
  private FileManager fileManager;
  @Autowired
  private RobotFileGeneratorService robotFileGeneratorService;
  @Autowired
  private GitService gitService;

  @Autowired
  private ConfigurationService configurationService;

  public List<TestCase> getAllTestCases() {
    return testCaseRepository.findAll();
  }

  public TestCase getTestCase(int id) {
    return testCaseRepository.findById(id).get();
  }

  @PostConstruct
  public void init() {
    Configuration configuration = this.configurationService.getConfiguration();
    this.url = configuration.getUrl();
    this.projectName = configuration.getProjectName();
    this.password = configuration.getPassword();
    relativePath = "/" + projectName + "/test/";
    homeDirectory = System.getProperty("user.home");
    path = homeDirectory + relativePath;
    projectPath = homeDirectory + "/" + projectName;
    libPath = path + "lib";
  }

  public TestCase addTestCase(TestCase testCase) {
    testCase = testCaseRepository.save(testCase);
    robotFileGeneratorService.generateRobotFile(testCase.getTestSuite().getId(), path);
    gitService.updateRemote();
    return testCase;
  }

  public void updateTestCase(TestCaseUpdateDto testCaseUpdateDto) {
    TestCase testCase = testCaseConverter.convertToEntity(testCaseUpdateDto.getTestCase());
    testCase.setTestSuite(testSuiteRepository.findByTestcaseId(testCase.getId()));
    for (TestCaseRowUpdateDto row : testCaseUpdateDto.getUpdatedTestCaseRows()) {
      if (row.isUpdated()) {
        TestCaseRow testCaseRow = this.testCaseRowConverter.convertToEntity(row.getTestCaseRow());
        testCaseRow.setTestCase(testCase);
        int id = this.testCaseRowRepository.save(testCaseRow).getId();
        row.getTestCaseRow().setId(id);
      }
    }
    if (testCaseUpdateDto.getDeletedTestCaseRows().size() > 0) {
      List<Integer> ids = testCaseUpdateDto.getDeletedTestCaseRows().stream()
          .map(value -> value.getTestCaseRow().getId()).filter(value -> value != null).toList();
      this.testCaseRowRepository.deleteAllById(ids);
    }
    List<Integer> testCaseRowOrder = testCaseUpdateDto.getUpdatedTestCaseRows().stream().
        map((e) -> e.getTestCaseRow().getId()).collect(Collectors.toList());
    testCase.setTestCaseRowOrder(testCaseRowOrder);
    //System.out.println(testCase.getTestSuite().getId());
    this.testCaseRepository.save(testCase);
    this.testCaseRowRepository.deleteByIdNotInAndTestCaseIdEquals(testCaseRowOrder,
        testCase.getId());
    robotFileGeneratorService.generateRobotFile(testCase.getTestSuite().getId(), path);
    gitService.updateRemote();
  }

  public void deleteTestCase(int id) {
    TestCase testCase = testCaseRepository.findById(id).get();
    testCaseRepository.deleteById(id);
    robotFileGeneratorService.generateRobotFile(testCase.getTestSuite().getId(), path);
    gitService.updateRemote();
  }

  public List<TestCaseRow> getTestCaseRowsByTestCaseId(int id) {
    Map<Integer, TestCaseRow> integerTestCaseRowMap = testCaseRowRepository.findBytestCaseId(id)
        .stream().collect(Collectors.toMap(TestCaseRow::getId, Function.identity()));
    List<TestCaseRow> testCaseRows = new ArrayList<>();
    List<Integer> testCaseRowOder = testCaseRepository.findById(id).get().getTestCaseRowOrder();
    for (int i = 0; i < testCaseRowOder.size(); i++) {
      testCaseRows.add(integerTestCaseRowMap.get(testCaseRowOder.get(i)));
    }
    return testCaseRows;
  }

  public Resource executeTestCase(int id) {
    TestCase testCase = getTestCase(id);
    System.out.println(testCase.getTestSuite().getId());
    StringTokenizer st = new StringTokenizer(url, "@");
    String newUrl = st.nextToken() + ":" + password + "@" + st.nextToken();
    System.out.println(newUrl);
    ProcessBuilder processBuilder = new ProcessBuilder(homeDirectory + "/clone.bash", newUrl,
        projectName);
    processBuilder.inheritIO();
    try {
      Process process;
      BufferedReader reader;
      String line;
            /*process=processBuilder.start();
            System.out.println("test");
            process.waitFor();
            System.out.println("test");
            reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ( (line = reader.readLine()) != null) {
                System.out.println(line);
            }
             */
      //robotFileGeneratorService.generateRobotFile(testCase.getTestSuite().getId(), path);
      System.out.println(path);
      System.out.println(testCase.getTestSuite().getName());
      System.out.println("'" + testCase.getName() + "'");
      String reportsPath =
          homeDirectory + "/reports/testSuite_" + testCase.getTestSuite().getId() + "/tc_"
              + testCase.getId();
      fileManager.makeDirectoryIfItDoesntExist(reportsPath);
      processBuilder.command(homeDirectory + "/run.bash", path, testCase.getTestSuite().getName(),
          testCase.getName(), reportsPath);
      process = processBuilder.start();
      process.waitFor();
      reader =
          new BufferedReader(new InputStreamReader(process.getInputStream()));
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
      String logPath = reportsPath + "/log.html";
      fileManager.replaceInFile(DELETED_STRING_REGEX, REPLACEMENT_STRING, logPath);
      File file = new File(logPath);
      Resource resource = new UrlResource(file.toURI());
      return resource;
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public TestCaseDto copyTestCase(TestCaseCloneDto testCaseCloneDto) {
    TestCase testcase = testCaseRepository.findById(testCaseCloneDto.getTestCaseId()).orElse(null);
    TestCase clonedTestCase = cloneTestCase(testcase);
    TestSuite testsuite = testSuiteRepository.findById(testCaseCloneDto.getTestSuiteId()).get();
    clonedTestCase.setName(testCaseCloneDto.getTestCaseName());
    clonedTestCase.setTestSuite(testsuite);
    List<TestCaseRow> testCaseRows = testCaseRowRepository.saveAll(
        clonedTestCase.getTestCaseRows());
    clonedTestCase.setTestCaseRowOrder(testCaseRows.stream()
        .map(TestCaseRow::getId).collect(Collectors.toList()));
    TestCase finalClonedTestCase = clonedTestCase;
    testCaseRows.forEach(testCaseRow -> testCaseRow.setTestCase(finalClonedTestCase));
    clonedTestCase.setTestCaseRows(testCaseRows);
    clonedTestCase = testCaseRepository.save(clonedTestCase);
    robotFileGeneratorService.generateRobotFile(clonedTestCase.getTestSuite().getId(), path);
    gitService.updateRemote();
    return testCaseConverter.convertToDto(clonedTestCase);
  }

  public TestCase cloneTestCase(TestCase testCase) {
    TestCase clonedTestCase = new TestCase();
    clonedTestCase.setName(testCase.getName());
    List<TestCaseRow> testCaseRows = robotFileGeneratorService.sortTestCaseRows(testCase);
    clonedTestCase.setTestCaseRows(testCaseRows.stream()
        .map(this::cloneTestCaseRow).collect(Collectors.toList()));
    return clonedTestCase;
  }

  public TestCaseRow cloneTestCaseRow(TestCaseRow testCaseRow) {
    TestCaseRow clonedTestCaseRow = new TestCaseRow();
    clonedTestCaseRow.setFunction(testCaseRow.getFunction());
    clonedTestCaseRow.setParameterValues(testCaseRow.getParameterValues());
    return clonedTestCaseRow;
  }
}
