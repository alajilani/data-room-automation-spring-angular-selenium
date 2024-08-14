package com.ads.robot.dto;

import java.util.List;

public class TestCaseRowDto {

    private Integer id;
    private List<String> parameterValues;

    private FunctionDto function;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(List<String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public FunctionDto getFunction() {
        return function;
    }

    public void setFunction(FunctionDto function) {
        this.function = function;
    }
}
