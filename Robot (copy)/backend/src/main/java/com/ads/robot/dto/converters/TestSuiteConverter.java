package com.ads.robot.dto.converters;

import com.ads.robot.dto.TestSuiteDto;
import com.ads.robot.entities.TestSuite;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TestSuiteConverter {
    @Autowired
    private TestCaseConverter testCaseConverter;
    private ModelMapper modelMapper = new ModelMapper();
    public TestSuiteDto convertToDto(TestSuite testSuite){
        TestSuiteDto testSuiteDto=modelMapper.map(testSuite,TestSuiteDto.class);
        testSuiteDto.setTestCases(testSuite.getTestCases().stream().map(testCase -> testCaseConverter.convertToDto(testCase))
                .collect(Collectors.toList()));
        return testSuiteDto;
    }
    public TestSuite convertToEntity(TestSuiteDto testSuiteDto){
        TestSuite testSuite=modelMapper.map(testSuiteDto,TestSuite.class);
        testSuite.setTestCases(testSuiteDto.getTestCases().stream().map(testCaseDto -> testCaseConverter.convertToEntity(testCaseDto))
                .collect(Collectors.toList()));
        testSuite.getTestCases().forEach(testCase -> testCase.setTestSuite(testSuite));
        return testSuite;

    }

}
