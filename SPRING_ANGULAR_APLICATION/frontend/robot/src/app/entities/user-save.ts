import { Role } from "./role";

export interface UserSave {
    id:number,
    firstName:string,
    lastName:String,
    username:string,
    password:string,
    roles:Role[],
    confirmPassword:string
}
