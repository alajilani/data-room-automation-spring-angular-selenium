import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MenuItem} from 'primeng/api';
import { Alert } from '../entities/alert';
import { AlertService } from '../services/alert.service';
import { TokenService } from '../services/token.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  topItems: MenuItem[];
  bottomItems: MenuItem[];
  userSubMenu: MenuItem[];

  alert:Alert={
    status:"",
    text:""
  }
  show=false;

  constructor(private tokenService:TokenService,private router:Router,private alertService:AlertService) { }

  ngOnInit() {
    this.topItems = [
      {
        label:'Home',
        icon:'pi pi-fw pi-home',
        command:()=>this.router.navigateByUrl("")
     
      },
      {
        separator:true
        },  
        {
        label:'Test Cases',
        icon:'pi pi-fw pi-file',
        command:()=>this.router.navigateByUrl("/testcase")
     
      },
    ]
    this.bottomItems = [
      {
        label:"Administation",
        icon: "pi pi-fw pi-th-large",
        items:[
          {
            label:"Users",
            icon:"pi pi-fw pi-users",
            command:()=>this.router.navigateByUrl("/admin/users")

          },
          {
            label:"Functions",
            icon:"pi pi-fw pi-book",
            command:()=>this.router.navigateByUrl("/admin/functions")

          },
          {
            label:"Configuration",
            icon:"pi pi-fw pi-cog",
            command:()=>this.router.navigateByUrl("/admin/configuration")

          },
          {
            label:"LOGIN PAGE",
            icon:"pi pi-fw pi-user-plus",
            command:()=>this.router.navigateByUrl("/admin/loginpage")

          },


        ]
      },
      {
      separator:true,
      },
      {
      label:this.tokenService.getUser().username.charAt(0).toUpperCase(),
      items:[
        {
          label:"Account",
          icon:"pi pi-fw pi-user"
        },
        {
          label:"Logout",
          icon:"pi pi-fw pi-sign-out",
          command:() =>this.logOut()
        },
      ]
      }
    ]
    this.alertService.alertChange.subscribe(alert =>{
      this.alert=alert
      this.show=true;
      setTimeout(() => this.show=false
      ,3000)
    })
  }

  logOut(){
    this.tokenService.clearToken();
    this.tokenService.clearUser();
    this.router.navigateByUrl("/login")
  }
}
