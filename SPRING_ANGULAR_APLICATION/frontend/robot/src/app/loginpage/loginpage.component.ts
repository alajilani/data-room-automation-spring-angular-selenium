import { Component } from '@angular/core';
import { LoginpageService } from '../services/loginpage.service';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent  {

  successMessage: string = "";
  errorMessage: string = "";
  username: string = '';
  password: string = '';
  success_page_name : string = '';
  url : string = '';
  constructor(private loginpageService: LoginpageService) {}

  onSubmit() {
    const loginData = {
      username: this.username,
      password: this.password,
      success_page_name : this.success_page_name,
      url: this.url
    };
    this.loginpageService.saveloginpage(loginData).subscribe(
        (response) => {
          console.log('Login successful', response);
          this.successMessage = "Successful update !";
        },
        (error) => {
          console.error('Login failed', error);
          this.errorMessage = "update failed";
        }
    );
  }



}