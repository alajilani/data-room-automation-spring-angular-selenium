import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../entities/user';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  isLoggedIn() :boolean {
    return typeof(this.getToken()) =="string" && this.getToken()!="";
  }

  storage:Storage=localStorage;
  constructor(private router:Router) {
    if(localStorage.getItem("token")!=null)
      this.storage=localStorage;
    else
      this.storage=sessionStorage
   }
  public stayLoggedIn(value:boolean){
    this.storage= value ? localStorage : sessionStorage;
  }
  public setUser(user: User): void {
    this.storage.setItem("user", JSON.stringify(user));
  }
  public setToken(token: string): void {
    this.storage.setItem("token", "Bearer " + token);
  }
  public getUser(): User | null {
    let userString = this.storage.getItem("user");
    if (userString == null) {
      return null;
    }
    let user: User = JSON.parse(userString)
    return user;
  }
  public getToken(): string {
    return this.storage.getItem("token") ?? "";
  }
  public isDoctor():boolean{
    try{
      return JSON.parse(this.storage.getItem("user")!).governorate == undefined?false:true;
    }catch(e){
      return false;
    }
  }
  public clearUser(){
    this.storage.removeItem("user");
  }
  public clearToken(){
    this.storage.removeItem("token");
  }
  public signOut(){
    this.clearUser();
    this.clearToken();
    //this.router.navigate(["/client/login"]);
  }
  public redirectIfNotSignedIn():void{
    if(this.getToken()==""){
      //this.router.navigate(["/client/login"]);
    }
  }
}
