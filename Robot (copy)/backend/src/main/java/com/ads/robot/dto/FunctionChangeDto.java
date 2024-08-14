package com.ads.robot.dto;

import java.util.List;

public class FunctionChangeDto {
    private int id;

    private String name;

    private List<String> oldParameters;
    private List<String> newParameters;
    private List<String> parameterMapping;

    private LibraryDto libraryDto;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOldParameters() {
        return oldParameters;
    }

    public void setOldParameters(List<String> oldParameters) {
        this.oldParameters = oldParameters;
    }

    public List<String> getNewParameters() {
        return newParameters;
    }

    public void setNewParameters(List<String> newParameters) {
        this.newParameters = newParameters;
    }

    public List<String> getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(List<String> parameterMapping) {
        this.parameterMapping = parameterMapping;
    }

    public LibraryDto getLibraryDto() {
        return libraryDto;
    }

    public void setLibraryDto(LibraryDto libraryDto) {
        this.libraryDto = libraryDto;
    }
}
