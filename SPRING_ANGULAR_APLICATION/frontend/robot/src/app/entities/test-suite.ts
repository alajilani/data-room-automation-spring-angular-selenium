import { TestCase } from "./test-case";

export interface TestSuite {
    id: number;
    name: string;
    testCases: TestCase[];
  
}

