import { Component, OnInit } from '@angular/core';
import { Role } from '../entities/role';
import { User } from '../entities/user';
import { UserEdit } from '../entities/user-edit';
import { UserSave } from '../entities/user-save';
import { RoleService } from '../services/role.service';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  constructor(private userService:UsersService,
    private roleService:RoleService) { }
  users:User[];
  loading:boolean = true;
  init=0;
  displayAddUserModal:boolean = false;
  userSave:UserSave={
    id:null,
    firstName:"",
    lastName:"",
    password:"",
    roles:[],
    username:"",
    confirmPassword:""
  };
  roles:Role[]=[];
  updatePassword=false;
  editUser=false;
  originalUser:User=null;
  ngOnInit() {
    this.userService.getUsers().subscribe(
      users => {
        this.users=users;
        this.loading=false;
        this.initUsers()
      },
      error => console.log(error)
    )
    this.roleService.getRoles().subscribe(
      roles => {
        this.roles=roles;
        this.initUsers()
      },
      error => console.log(error)
    )
  }
  initUsers() {
    this.init++;
    if(this.init==2){
      this.users.forEach(user => this.initUserRoles(user));
    }
  }
  initUserRoles(user:User){
    let tempRoles = []
    user.roles.forEach(role => tempRoles.push(this.roles.find(r=>r.id==role.id)));
    user.roles=tempRoles;
    return user;
  }
  showAddUserDialog(){
    this.displayAddUserModal=true;
    this.editUser=false;
    this.userSave={
      id:null,
      firstName:"",
      lastName:"",
      password:"",
      roles:[],
      username:"",
      confirmPassword:""
    };
  
  }
  showEditUserDialog(user:User){
    this.displayAddUserModal=true;
    this.editUser=true;
    this.userSave.id=user.id;
    this.userSave.roles=user.roles;
    this.userSave.username=user.username;
    this.userSave.firstName=user.firstName;
    this.userSave.lastName=user.lastName;
    this.updatePassword=false;
    this.originalUser=user;
  }

  saveOrUpdateUser(){
    if(this.editUser){
      console.log("editing")
      this.updateUser();
    }else{
      this.saveUser()
    }
  }
  updateUser() {
    let userEdit:UserEdit={
      updatePassword:this.updatePassword,
      user:this.userSave
    }
    this.userService.updateUser(userEdit).subscribe(
      user =>  this.closeEditDialog(true,user),
      error => console.log(error)
    );
  }
  saveUser(){
    if(this.validUserSave()){
      this.userService.saveUser(this.userSave).subscribe(
        user => {
          this.users.push(this.initUserRoles(user));
          this.closeEditDialog(true);
        },
        error => console.log(error)
      )
    }
  }
  closeEditDialog(sucess:boolean,user?:User){
    this.displayAddUserModal = false;
    if(sucess===false){
      return;
    }
    if(user!=null){
      this.originalUser.id=user.id;
      this.originalUser.roles=user.roles;
      this.originalUser.username=user.username;
      this.originalUser.firstName=user.firstName;
      this.originalUser.lastName=user.lastName;
    }

  }
  validUserSave(){
    return this.userSave!= null&& this.validUsername && this.validPassword() && this.confirmPassword();
  }
  confirmPassword() {
    return this.userSave.password===this.userSave.confirmPassword;
  }
  validPassword() {
    return this.userSave.password!= "" && this.userSave.password != null;
  }
  validUsername() {
    return this.userSave.username!= "" && this.userSave.username != null;
  }
  userRolesToString(roles:Role[]){
    let string="";
    roles.forEach(role => string+=","+role.name);
    string=string.slice(1);
    return string;
  }

  deleteUser(user:User){
    this.userService.deleteUser(user.id).subscribe(
      response => this.deleteLocalUser(user),
      error => console.log(error)
    )
  }
  deleteLocalUser(user:User){
    let index=this.users.findIndex(u => u.id==user.id);
    if(index>-1){
      this.users.splice(index,1);
    }
  }
}
