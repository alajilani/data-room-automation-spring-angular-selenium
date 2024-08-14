package com.ads.robot.controllers;

import com.ads.robot.dto.LibraryDto2;
import com.ads.robot.dto.converters.LibraryConverter;
import com.ads.robot.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private LibraryConverter libraryConverter;
    @GetMapping(value = "/get/all")
    public List<LibraryDto2> getAllFunction() {
        List<LibraryDto2> libraries =libraryService.getLibraryList().stream().map(libraryConverter::convertToDto2).collect(Collectors.toList());
        return libraries;
    }

}
