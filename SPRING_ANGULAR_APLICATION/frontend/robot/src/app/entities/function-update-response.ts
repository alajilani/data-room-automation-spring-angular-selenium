import { FunctionChange } from "./function-change";
import { MissingFunction } from "./missing-function";
import { TestFunction } from "./test-function";

export interface FunctionUpdateResponse {
    missingFunctions:MissingFunction[],
    newFunctions:TestFunction[],
    changedFunctions:FunctionChange[],
}
