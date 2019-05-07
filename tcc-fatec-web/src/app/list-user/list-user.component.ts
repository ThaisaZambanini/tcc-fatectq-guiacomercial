import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../service/user.service";
import {Usuario} from "../model/user.model";


@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  users: Usuario[];

  constructor(private router: Router, private userService: UserService) { }
  pageActual = 1;

  ngOnInit() {
    // this.userService.getUsers()
    //   .subscribe( data => {
    //     this.users = data;
    //   });
  }

  deleteUser(user: Usuario): void {
    // this.userService.deleteUser(user.id)
    //   .subscribe( data => {
    //     this.users = this.users.filter(u => u !== user);
    //   })
  };

  editUser(user: Usuario): void {
    localStorage.removeItem("editUserId");
    // localStorage.setItem("editUserId", user.id.toString());
    this.router.navigate(['edit-user']);
  };

  addUser(): void {
    this.router.navigate(['add-user']);
  };
}
