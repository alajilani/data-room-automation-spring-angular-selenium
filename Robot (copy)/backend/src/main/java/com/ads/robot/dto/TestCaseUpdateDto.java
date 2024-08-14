package com.ads.robot.dto;

import java.util.List;

public class TestCaseUpdateDto {

    private TestCaseDto testCase;
    private List<TestCaseRowUpdateDto> updatedTestCaseRows;
    private List<TestCaseRowUpdateDto> deletedTestCaseRows;

    public TestCaseDto getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCaseDto testCase) {
        this.testCase = testCase;
    }

    public List<TestCaseRowUpdateDto> getUpdatedTestCaseRows() {
        return updatedTestCaseRows;
    }

    public void setUpdatedTestCaseRows(List<TestCaseRowUpdateDto> updatedTestCaseRows) {
        this.updatedTestCaseRows = updatedTestCaseRows;
    }

    public List<TestCaseRowUpdateDto> getDeletedTestCaseRows() {
        return deletedTestCaseRows;
    }

    public void setDeletedTestCaseRows(List<TestCaseRowUpdateDto> deletedTestCaseRows) {
        this.deletedTestCaseRows = deletedTestCaseRows;
    }
}
