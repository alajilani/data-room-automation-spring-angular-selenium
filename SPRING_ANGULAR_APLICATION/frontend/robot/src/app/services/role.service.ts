import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { Role } from '../entities/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

constructor(private httpClient:HttpClient) { }

getRoles(){
  return this.httpClient.get<Role[]>(BASE_URL+"/role/get/all");
}
}
