package com.ads.robot.controllers;

import com.ads.robot.dto.TestCaseCloneDto;
import com.ads.robot.dto.TestCaseDto;
import com.ads.robot.dto.TestCaseRowDto;
import com.ads.robot.dto.TestCaseUpdateDto;
import com.ads.robot.dto.converters.TestCaseRowConverter;
import com.ads.robot.entities.TestCase;
import com.ads.robot.services.TestCaseService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "testcase")
public class TestCaseController {

  @Autowired
  private TestCaseService testCaseService;
  @Autowired
  private TestCaseRowConverter testCaseRowConverter;

  @GetMapping(value = "/get/all")
  public List<TestCase> getAllTestCase() {
    return testCaseService.getAllTestCases();
  }

  @GetMapping(value = "/{id}/testcaserow/get/all")
  public List<TestCaseRowDto> getTestCaseRowsByTestCaseId(@PathVariable int id) {
    return testCaseService.getTestCaseRowsByTestCaseId(id)
        .stream().map(testCaseRowConverter::convertToDto).collect(Collectors.toList());
  }

  @GetMapping("get/{id}")
  public TestCase getTestCase(@PathVariable int id) {
    return testCaseService.getTestCase(id);
  }

  @PostMapping(value = "/save")
  public void addTestCase(@RequestBody TestCase testCase) {
    testCaseService.addTestCase(testCase);
  }

  @PutMapping(value = "/update")
  public void updateTestCase(@RequestBody TestCaseUpdateDto testCaseUpdateDto) {
    testCaseService.updateTestCase(testCaseUpdateDto);
  }

  @DeleteMapping("delete/{id}")
  public void deleteTestCase(@PathVariable int id) {
    testCaseService.deleteTestCase(id);
  }

  @GetMapping("execute/{id}")
  public ResponseEntity executeTestCase(@PathVariable int id) {
    return new ResponseEntity<>(testCaseService.executeTestCase(id), HttpStatus.OK);
  }

  @PostMapping("clone")
  public ResponseEntity<TestCaseDto> cloneTestCase(@RequestBody TestCaseCloneDto testCaseCloneDto) {
    return new ResponseEntity<>(testCaseService.copyTestCase(testCaseCloneDto), HttpStatus.OK);
  }
}
