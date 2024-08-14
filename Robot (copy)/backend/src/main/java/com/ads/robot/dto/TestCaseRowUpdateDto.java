package com.ads.robot.dto;

public class TestCaseRowUpdateDto {

    private TestCaseRowDto TestCaseRow;
    private boolean Updated;

    public boolean isUpdated() {
        return Updated;
    }

    public void setUpdated(boolean updated) {
        Updated = updated;
    }

    public TestCaseRowDto getTestCaseRow() {
        return TestCaseRow;
    }

    public void setTestCaseRow(TestCaseRowDto testCaseRow) {
        TestCaseRow = testCaseRow;
    }
}
