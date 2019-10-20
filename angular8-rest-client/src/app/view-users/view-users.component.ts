import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Response } from '../shared/Response';
import { User } from '../shared/User'
import { ResponseService } from '../shared/response.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})

export class ViewUsersComponent implements OnInit {
  static STATUS_CREATE = 'create';
  static STATUS_EDIT = 'edit';
  static STATUS_NONE = 'none';
  response: Response;
  users: any = [];
  formUser: any = null;
  inputUsername: any = null;
  inputEmail: any = null;
  inputPassword: any = null;
  inputFirstName: any = null;
  inputLastName: any = null;
  inputPhoneNumber: any = null;
  inputAge: any = null;
  checkIsActive: any = null;
  tittle: any = null;
  instance: any = null;
  btnRegister: any = null;
  status: String = ViewUsersComponent.STATUS_CREATE;
  constructor(private restApi: ResponseService) {
  }
  changeStatus(status: string) {
    switch (status) {
      case ViewUsersComponent.STATUS_CREATE:
        this.btnRegister.addClass("d-none");
        this.formUser.removeClass("d-none");
        this.tittle.text('Sing Up');
        this.inputUsername.prop('readonly', false);
        break;
      case ViewUsersComponent.STATUS_EDIT:
        this.btnRegister.addClass("d-none");
        this.formUser.removeClass("d-none");
        this.tittle.text('Edit user');
        this.inputUsername.prop('readonly', true);
        break;
      case ViewUsersComponent.STATUS_NONE:
        this.formUser.addClass("d-none");
        this.btnRegister.removeClass("d-none");
        break;
    }
  }
  printUser(user: User) {
    this.inputUsername.val(user.username);
    this.inputFirstName.val(user.firstName);
    this.inputLastName.val(user.lastName);
    this.inputEmail.val(user.email);
    //this.inputPassword.val(user.password);
    this.inputPhoneNumber.val(user.phoneNumber);
    this.inputAge.val(user.age);
    this.checkIsActive.prop('checked', user.active);
  }

  loadUser(username: String) {
    this.changeStatus(ViewUsersComponent.STATUS_EDIT);
    let userToEdit = this.searchUserByUsername(username);
    this.printUser(userToEdit);

    this.inputUsername.prop('readonly', true);//removeAttr
  }
  openMakeUser() {
    this.changeStatus(ViewUsersComponent.STATUS_CREATE);
    this.printUser(new User);
  }
  loadUsers() {
    console.log('loadUsers')
    return this.restApi.getUsers().subscribe((response: Response) => {
      this.users = response.object;
      this.response = response;
      console.log('response', response)
    })
  }
  searchUserByUsername(username: String) {
    console.log('searchUserByUsername', username, this.users)
    let userFound = null;
    if (this.users && this.users.length > 0) {
      console.log('buscando usuario')
      this.users.forEach(user => {
        console.log(user.username.valueOf() + '==' + username.valueOf(), (user.username.valueOf() == username.valueOf()))
        if (user.username.valueOf() == username.valueOf()) {
          console.log('return', user);
          userFound = user;
        }
      });
    }
    console.log('return null');
    return userFound;
  }
  deleteUser(username: String) {
    let userToDelete = this.searchUserByUsername(username);
    console.log('deleteUser', userToDelete)
    if (userToDelete) {
      this.restApi.deleteUser(userToDelete.username).subscribe((response: Response) => {
        console.log('User delete', response)
        this.instance.loadUsers();
      })
    }
  }
  makeUser() {
    let newUser = new User();
    newUser.username = this.inputUsername.val();
    newUser.firstName = this.inputFirstName.val();
    newUser.lastName = this.inputLastName.val();
    newUser.email = this.inputEmail.val();
    newUser.password = this.inputPassword.val();
    newUser.phoneNumber = parseInt(this.inputPhoneNumber.val());
    newUser.age = parseInt(this.inputAge.val());
    newUser.active = this.checkIsActive.is(":checked");
    console.log('makeUser', newUser);
    return newUser;
  }
  createUser(user: User) {
    console.log('createUser-->', user)
    return this.restApi.createUser(user).subscribe((response: Response) => {
      console.log('user', response)
      this.instance.loadUsers();
      this.instance.changeStatus(ViewUsersComponent.STATUS_NONE);
    })
  }
  onCancel($event) {
    this.changeStatus(ViewUsersComponent.STATUS_NONE);

  }
  onSubmit($event) {
    $event.preventDefault();
    switch (this.status) {
      case ViewUsersComponent.STATUS_CREATE:
        let newUser = this.makeUser();
        this.createUser(newUser);
        break;
    }

  }
  ngOnInit() {
    this.instance = this;
    this.formUser = $('#formUser');
    this.inputUsername = $('#inputUsername');
    this.inputEmail = $('#inputEmail');
    this.inputPassword = $('#inputPassword');
    this.inputFirstName = $('#inputFirstName');
    this.inputLastName = $('#inputLastName');
    this.inputPhoneNumber = $('#inputPhoneNumber');
    this.inputAge = $('#inputAge');
    this.checkIsActive = $('#checkIsActive');
    this.tittle = $('#tittle');
    this.btnRegister = $('#btnRegister');

    this.changeStatus(ViewUsersComponent.STATUS_NONE);
    this.loadUsers();
  }
}
