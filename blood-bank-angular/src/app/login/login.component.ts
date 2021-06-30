import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDetails } from '../LoginDetails';
import { UserLoginService } from '../user-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  logindetails: LoginDetails = new LoginDetails("", "");
  receivedMessage: any;

  constructor(private service: UserLoginService, private router: Router) { }

  ngOnInit(): void {
  }
  public getAccessToken() {
    let resp = this.service.generateToken(this.logindetails);
    resp.subscribe(data => {
      sessionStorage.setItem("token", data);
      this.router.navigate(["/home"]);
    },
      (err: HttpErrorResponse) => { this.receivedMessage = "bad credientials" });
  }
  public goToRegisterPage() {
      this.router.navigate(["/register"]);
  } 

}
