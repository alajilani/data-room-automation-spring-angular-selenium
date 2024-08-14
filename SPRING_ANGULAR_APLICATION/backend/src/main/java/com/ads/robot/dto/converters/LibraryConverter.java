package com.ads.robot.dto.converters;

import com.ads.robot.dto.LibraryDto;
import com.ads.robot.dto.LibraryDto2;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.entities.FunctionRobot;
import com.ads.robot.entities.Library;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryConverter {
    @Lazy
    @Autowired
    private FunctionConverter functionConverter;

    private ModelMapper modelMapper = new ModelMapper();
    public LibraryDto convertToDto(Library library){
        return modelMapper.map(library, LibraryDto.class);
    }
    public LibraryDto2 convertToDto2(Library library){
        List<FunctionRobot> functions = library.getFunctions();
        library.setFunctions(null);
        LibraryDto2 libraryDto2= modelMapper.map(library, LibraryDto2.class);
        libraryDto2.setFunctions(functions.stream().map(functionConverter::convertToDto).collect(Collectors.toList()));
        return  libraryDto2;
    }

    public Library convertToEntity(LibraryDto libraryDto){
        return modelMapper.map(libraryDto,Library.class);

    }

}
