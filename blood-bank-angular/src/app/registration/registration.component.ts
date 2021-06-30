import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../User';
import { UserRegistrationService } from '../user-registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {


  user: User = new User("", "", "", "", "", "", "");
  receivedMessage: any
  constructor(private service: UserRegistrationService, private router: Router) { }

  ngOnInit(): void {
  }

  public registerNow() {
    let response = this.service.doRegistration(this.user);
    response.subscribe((data) => {
      this.receivedMessage = data;
      if (this.receivedMessage = 'User Registered Successfully') {
        this.router.navigate(["/login"])
      }
    });
  }
  public goToLoginPage() {
    this.router.navigate(["/login"]);
  }
}
