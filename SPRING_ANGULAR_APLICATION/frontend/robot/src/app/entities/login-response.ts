import { User } from "./user";

export interface LoginResponse {
  jwttoken:string;
  user:User;
}
