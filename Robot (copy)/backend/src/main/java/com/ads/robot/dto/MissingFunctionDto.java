package com.ads.robot.dto;

import java.util.List;

public class MissingFunctionDto {
    private FunctionDto missingFunction;
    private FunctionDto newFunction;
    private List<String> parameterMapping;

    public FunctionDto getMissingFunction() {
        return missingFunction;
    }

    public void setMissingFunction(FunctionDto missingFunction) {
        this.missingFunction = missingFunction;
    }

    public FunctionDto getNewFunction() {
        return newFunction;
    }

    public void setNewFunction(FunctionDto newFunction) {
        this.newFunction = newFunction;
    }

    public List<String> getParameterMapping() {
        return parameterMapping;
    }

    public void setParameterMapping(List<String> parameterMapping) {
        this.parameterMapping = parameterMapping;
    }
}
