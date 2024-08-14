package com.ads.robot.dto.converters;

import com.ads.robot.dto.TestCaseRowDto;
import com.ads.robot.entities.TestCaseRow;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestCaseRowConverter {
    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private FunctionConverter functionConverter;
    public TestCaseRowDto convertToDto(TestCaseRow testCaseRow){
        TestCaseRowDto testCaseRowDto= modelMapper.map(testCaseRow,TestCaseRowDto.class);
        testCaseRowDto.setFunction(functionConverter.convertToDto(testCaseRow.getFunction()));
        return testCaseRowDto;
    }
    public TestCaseRow convertToEntity(TestCaseRowDto testCaseRowDto){
        TestCaseRow testCaseRow= modelMapper.map(testCaseRowDto,TestCaseRow.class);
        testCaseRow.setFunction(functionConverter.convertToEntity(testCaseRowDto.getFunction()));
        return testCaseRow;
    }

}
