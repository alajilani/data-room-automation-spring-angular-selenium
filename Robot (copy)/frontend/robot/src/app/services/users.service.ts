import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { User } from '../entities/user';
import { UserEdit } from '../entities/user-edit';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

constructor(private httpClient:HttpClient) {}

getUsers(){
  return this.httpClient.get<User[]>(BASE_URL+"/user/get/all");
}
saveUser(userSave){
  return this.httpClient.post<User>(BASE_URL+"/user/save",userSave);
}
deleteUser(userId:number){
  return this.httpClient.delete(BASE_URL+"/user/delete/"+userId);
}
updateUser(userEdit:UserEdit){
  return this.httpClient.put<User>(BASE_URL+"/user/update",userEdit);
}

}
