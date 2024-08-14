import { TestFunction } from "./test-function";

export interface TestCaseRow {
    function: TestFunction;
    id: number;
    parameterValues: string[];  
}