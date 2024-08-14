import { LibraryDto } from "./libraryDto";

export interface TestFunction {
    id: number,
    name: string,
    parameters: string[],
    library:LibraryDto
}
