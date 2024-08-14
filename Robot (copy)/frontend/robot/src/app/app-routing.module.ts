import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfigurationComponent } from './configuration/configuration.component';
import { FunctionsComponent } from './functions/functions.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './services/auth-guard.service';
import { TestListComponent } from './test-list/test-list.component';
import { UsersComponent } from './users/users.component';
import {LoginpageComponent} from "./loginpage/loginpage.component";

const routes: Routes = [
  { path: "login", component: LoginComponent },
  {
    path: "", component: HomeComponent,
    canActivate: [AuthGuardService],
    children: [
      { path: "testcase", component: TestListComponent },
      { path: "admin/users", component: UsersComponent },
      { path: "admin/functions", component: FunctionsComponent },
      { path: "admin/configuration", component: ConfigurationComponent },
      { path: "admin/loginpage", component: LoginpageComponent}
    ]
  },
  { path: "**", redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
