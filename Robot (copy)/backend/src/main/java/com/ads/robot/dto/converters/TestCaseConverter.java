package com.ads.robot.dto.converters;

import com.ads.robot.dto.TestCaseDto;
import com.ads.robot.entities.TestCase;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TestCaseConverter {
    private ModelMapper modelMapper = new ModelMapper();
    public TestCaseDto convertToDto(TestCase testCase){
        return modelMapper.map(testCase,TestCaseDto.class);
    }
    public TestCase convertToEntity(TestCaseDto testCaseDto){
        return modelMapper.map(testCaseDto,TestCase.class);

    }

}
