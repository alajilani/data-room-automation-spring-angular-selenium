import { FunctionChange } from "./function-change";
import { TestFunction } from "./test-function";

export interface FunctionUpdate {
    missingFunctions:TestFunction[],
    newFunctions:TestFunction[],
    changedFunctions:FunctionChange[],
    
}
