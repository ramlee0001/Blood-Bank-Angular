import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from '../home.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  viewprofilemessage: string = "";
  donatebloodmessage: string = "";
  requestbloodmessage: string = "";
  bloodgroup: string = "";
  constructor(private service: HomeService, private router: Router) { }

  ngOnInit(): void {
  }
  public viewprofile() {
    var token: any = sessionStorage.getItem("token")
    let resp = this.service.viewprofileservice(token);
    resp.subscribe(data => this.viewprofilemessage = data);
  }
  public donateblood() {
    var token:any = sessionStorage.getItem("token")
    let resp = this.service.donatebloodservice(token);
    resp.subscribe(data =>this.donatebloodmessage=data);   
  }
  public requestblood() {
    var token: any = sessionStorage.getItem("token")
    let resp = this.service.requestbloodservice(token, this.bloodgroup);
    resp.subscribe(data => this.requestbloodmessage = data);
  }
  public logout() {
    sessionStorage.removeItem("token");
    this.router.navigate(["/login"]);
  }
}
