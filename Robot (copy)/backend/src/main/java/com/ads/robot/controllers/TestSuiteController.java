package com.ads.robot.controllers;

import com.ads.robot.dto.TestCaseDto;
import com.ads.robot.dto.TestSuiteDto;
import com.ads.robot.dto.converters.TestCaseConverter;
import com.ads.robot.dto.converters.TestSuiteConverter;
import com.ads.robot.entities.TestCase;
import com.ads.robot.entities.TestSuite;
import com.ads.robot.services.TestSuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping(value = "testsuite")
public class TestSuiteController {


    @Autowired
    private TestSuiteService testSuiteService;
    @Autowired
    private TestSuiteConverter testSuiteConverter;

    @Autowired
    private TestCaseConverter testCaseConverter;


    @GetMapping(value = "/get/all")
    public List<TestSuiteDto> getAllTestSuite() {
        return testSuiteService.getAllTestSuites().stream().map(testSuiteConverter::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("get/{id}")
    public TestSuite getTestSuite(@PathVariable int id){
        return testSuiteService.getTestSuite(id);
    }

    @PostMapping(value = "/save")
    public TestSuiteDto addTestSuite(@RequestBody TestSuiteDto testSuiteDto) {
        TestSuite testSuite  =  testSuiteService.addTestSuite(testSuiteConverter.convertToEntity(testSuiteDto));
        TestSuiteDto savedTestSuiteDto = testSuiteConverter.convertToDto(testSuite);
        return savedTestSuiteDto;
    }
    @PostMapping(value = "/{id}/testcase/save")
    public TestCaseDto addTestSuite(@RequestBody TestCaseDto TestCaseDto,@PathVariable int id) {
        TestCase testCase = testCaseConverter.convertToEntity(TestCaseDto);
        return testCaseConverter.convertToDto(testSuiteService.addTestCase(testCase,id));
    }


    @PutMapping(value = "/update")
    public void updateTestSuite(@RequestBody TestSuite testSuite) {
        testSuiteService.updateTestSuite(testSuite);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTestSuite(@PathVariable int id) {
        testSuiteService.deleteTestSuite(id);
    }

}
