package com.ads.robot.services;

import com.ads.robot.Utils.FileManager;
import com.ads.robot.entities.TestCase;
import com.ads.robot.entities.Loginpage;
import com.ads.robot.entities.TestCaseRow;
import com.ads.robot.entities.TestSuite;
import com.ads.robot.repositories.TestCaseRowRepository;
import com.ads.robot.repositories.TestSuiteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotFileGeneratorService {

  private String HEADER = """
            
      *** Settings ***
      Resource          Login/LoginPage.robot
      Library           lib/Inputs.py
      Library           lib/Clickables.py
      Library           lib/Window.py
   
      Library           lib/Selection.py
      Library           lib/Verification.py
   
      Test Teardown     Close All Browsers
       
       
      *** Variables ***
      *** Test Cases ***
                  """;


  private String text1 = """
          *** Settings ***
          Library           SeleniumLibrary
          Library           ../Config/BrowserOptions.py
          Library           ../lib/Window.py
                    
          *** Variables ***
          ${proton_mail_url}    https://mail.protonmail.com/
          ${username_field_locator}    //input[@name='username']
          ${password_field_locator}      //input[@name='password']
          ${login_button_locator}    //button
          ${forgot_password_locator}    //div[contains(@class, 'container')]/span[contains(@class, 'psw')]    
          ${reset_email_password_locator}    id:email-input
          ${gmail_email_field_locator}    //input[@type="email"]
          ${failed_page_name}    Error
          """;
  @Autowired
  private TestSuiteRepository testSuiteRepository;
  @Autowired
  private TestCaseRowRepository testCaseRowRepository;
  @Autowired
  private FileManager fileManager;

  public void generateRobotFile(int testSuiteId, String path) {
    TestSuite testSuite = testSuiteRepository.findById(testSuiteId).get();
    String fileName = testSuite.getName() + ".robot";
    List<TestCase> testCases = testSuite.getTestCases();
    String fileText = HEADER;
    for (TestCase testCase : testCases) {
      fileText += testCase.getName() + "\n";
      fileText += "    Make Valid Login\n";

      List<TestCaseRow> testCaseRows = sortTestCaseRows(testCase);
      for (TestCaseRow row : testCaseRows) {
        if (row == null) {
          System.out.println("null");
        }
        String functionName = row.getFunction().getName();
        if (functionName.equals("Get Global Param") || functionName.equals("Get Param")
            || functionName.equals("Get Test") || functionName.equals("Get Test Param")) {
          fileText += String.format("    ${%s}=", row.getParameterValues().get(0));
          row.getParameterValues().set(0, "DS_" + row.getParameterValues().get(0));
        }
        fileText += "    " + functionName;
        for (String parameter : row.getParameterValues()) {
          if (parameter != null) {
            fileText += "    " + parameter;
          }
        }
        fileText += "\n";
      }
    }
    fileManager.writeToFile(path + fileName, fileText);

  }

  List<TestCaseRow> sortTestCaseRows(TestCase testCase) {
    List<TestCaseRow> originalTestCaseRows = testCaseRowRepository.findBytestCaseId(
        testCase.getId());
    Map<Integer, TestCaseRow> integerTestCaseRowMap = originalTestCaseRows.stream()
        .collect(Collectors.toMap(TestCaseRow::getId, Function.identity()));
    List<TestCaseRow> testCaseRows = new ArrayList<>(integerTestCaseRowMap.size());
    List<Integer> testCaseRowOder = testCase.getTestCaseRowOrder();
    for (int i = 0; i < testCaseRowOder.size(); i++) {
      testCaseRows.add(integerTestCaseRowMap.get(testCaseRowOder.get(i)));
    }
    return testCaseRows;

  }

  public void generateloginparametrs(Loginpage loginpage, String path) {
    String username= loginpage.getUsername();
    String password= loginpage.getPassword();
    String url= loginpage.getUrl();
    String success_page_name=loginpage.getSuccess_page_name();

    String fileText1 = text1;
      fileText1 +="${url}"+ "    "+url+"\n";
      fileText1 +="${success_page_name}"+ "    "+success_page_name+"\n";
      fileText1 +="${valid_username}"+ "    "+username+"\n";
      fileText1 +="${valid_password}"+ "    "+password+"\n";

      fileText1 +="*** Keywords ***\n" +
              "Open Login Page\n" +
              "    ${options}    get firefox options\n" +
              "    ${exec_path}    get firefox driver path\n" +
              "    open browser    ${url}     executable_path=${exec_path}    options=${options}   #ff_profile_dir=/home/achreftrabelsi/.mozilla/firefox/10nblqb2.default-release\n" +
              "    maximize\n" +
              "Fill Login Info\n" +
              "    wait    3\n" +
              "    [Arguments]    ${username}    ${password}\n" +
              "    input text    ${username_field_locator}    ${username}\n" +
              "    input text    ${password_field_locator}    ${password}\n" +
              "\n" +
              "Click Login\n" +
              "    click button    ${login_button_locator}\n" +
              "\n" +
              "Verify Login Success\n" +
              "    Wait Until Keyword Succeeds    2s    500ms    title should be    ${success_page_name}\n" +
              "\n" +
              "Verify Login Failed\n" +
              "    Wait Until Keyword Succeeds    2s    500ms    title should be    ${failed_page_name}\n" +
              "\n" +
              "Verify Remember Me Clicked Success\n" +
              "    close all browsers\n" +
              "    open login page\n" +
              "    verify login success\n" +
              "\n" +
              "Verify Remember Me Not Clicked Success\n" +
              "    close all browsers\n" +
              "    open login page\n" +
              "    Wait Until Keyword Succeeds    2s    500ms    page should contain element    ${password_field_locator}\n" +
              "    close all browsers\n" +
              "\n" +
              "Click Remember Me\n" +
              "    Click Element    ${remember_me_checkbox_locator}\n" +
              "\n" +
              "Make Valid Login\n" +
              "    open login page\n" +
              "    fill login info    ${valid_username}    ${valid_password}\n" +
              "    click login\n" +
              "    Wait Until Keyword Succeeds    5s    500ms    title should be    ${success_page_name}\n" +
              "\n" +
              "Click Forgot Password\n" +
              "    click element    ${forgot_password_locator}\n";

    fileManager.writeToFile(path,fileText1);

  }



}