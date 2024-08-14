import { TestCase } from "./test-case";
import { TestCaseRowDto } from "./test-case-row-dto";

export interface TestCaseUpdate {
    deletedTestCaseRows: TestCaseRowDto[];
    testCase: TestCase;
    updatedTestCaseRows: TestCaseRowDto[];  
}

