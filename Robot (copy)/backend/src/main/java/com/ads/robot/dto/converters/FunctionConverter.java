package com.ads.robot.dto.converters;

import com.ads.robot.dto.FunctionDto;
import com.ads.robot.dto.LibraryDto;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.entities.Library;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FunctionConverter {
    @Autowired
    LibraryConverter libraryConverter;
    private ModelMapper modelMapper = new ModelMapper();
    public FunctionDto convertToDto(FunctionRobot function){
        FunctionDto functionDto= modelMapper.map(function,FunctionDto.class);
        LibraryDto libraryDto = libraryConverter.convertToDto(function.getLibrary());
        functionDto.setLibrary(libraryDto);
        return functionDto;
    }
    public FunctionRobot convertToEntity(FunctionDto functionDto){
        FunctionRobot function = modelMapper.map(functionDto,FunctionRobot.class);
        Library library = libraryConverter.convertToEntity(functionDto.getLibrary());
        function.setLibrary(library);
        return  function;
    }

}
