import {TestCase} from "./test-case";

export class SelectedTestCase {
    testCase: TestCase;
    testSuiteName: string;

    constructor(testCase: TestCase, testSuiteName: string) {
        this.testCase = testCase;
        this.testSuiteName = testSuiteName;
    }
}