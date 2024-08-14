import { LibraryDto } from "./libraryDto";

export interface FunctionChange {
    id: number,
    name: string,
    oldParameters: string[],
    newParameters: string[],
    parameterMapping:string[],
    library:LibraryDto
}
