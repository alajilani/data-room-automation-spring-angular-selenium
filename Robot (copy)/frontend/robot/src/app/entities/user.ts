import { Role } from './role';
export interface User{
    id:number,
    firstName:string,
    lastName:String,
    username:string,
    roles:Role[]
}
