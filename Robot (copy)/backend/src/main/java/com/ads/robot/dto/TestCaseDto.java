package com.ads.robot.dto;

import java.util.List;

public class TestCaseDto {
    private int id;

    private String name;

    private List<Integer> TestCaseRowOrder;


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

    public List<Integer> getTestCaseRowOrder() {
        return TestCaseRowOrder;
    }

    public void setTestCaseRowOrder(List<Integer> testCaseRowOrder) {
        TestCaseRowOrder = testCaseRowOrder;
    }
}
