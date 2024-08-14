import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../entities/login';
import { SignInService } from '../services/sign-in.service';
import { TokenService } from '../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginInfo: Login = {
    username: "",
    password: "",
    stayLoggedin: false,
  }
    errorMessage: string = "";
  constructor(private tokenService: TokenService,
     private signInService: SignInService,
     private router:Router) { }

  ngOnInit(): void {
    if(this.tokenService.isLoggedIn()){
      this.router.navigateByUrl("");
    }
  }
  login(): void {
      this.signInService.login(this.loginInfo).subscribe(
          loginResponse => {
              this.tokenService.stayLoggedIn(this.loginInfo.stayLoggedin);
              this.tokenService.setUser(loginResponse.user);
              this.tokenService.setToken(loginResponse.jwttoken);
              this.router.navigateByUrl("/testcase")
          }, error => {
              this.errorMessage = "Invalid username or password";
          }
      );
  }
}
