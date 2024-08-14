package com.ads.robot.controllers;

import com.ads.robot.entities.TestCaseRow;
import com.ads.robot.services.TestCaseRowService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "testcaserow")
public class TestCaseRowController {

  @Autowired
  private TestCaseRowService testCaseRowService;

  @GetMapping(value = "/get/all")
  public List<TestCaseRow> getAllTestCaseRow() {
    return testCaseRowService.getAllTestCaseRows();
  }

  @GetMapping("get/{id}")
  public TestCaseRow getTestCaseRow(@PathVariable int id) {
    return testCaseRowService.getTestCaseRow(id);
  }

  @PostMapping(value = "/save")
  public void addTestCaseRow(@RequestBody TestCaseRow testCaseRow) {
    testCaseRowService.addTestCaseRow(testCaseRow);
  }

  @PutMapping(value = "/update")
  public void updateTestCaseRow(@RequestBody TestCaseRow testCaseRow) {
    testCaseRowService.updateTestCaseRow(testCaseRow);
  }

  @DeleteMapping("delete/{id}")
  public void deleteTestCaseRow(@PathVariable int id) {
    testCaseRowService.deleteTestCaseRow(id);
  }

}