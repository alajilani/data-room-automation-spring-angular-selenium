import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { loginpage } from '../entities/loginpage';



@Injectable({
  providedIn: 'root'
})
export class LoginpageService {

  constructor(private httpClient:HttpClient) {}


  saveloginpage(loginpagesave:any){
    return this.httpClient.post<loginpage>(BASE_URL+"/loginpage/save",loginpagesave);
  }
}
