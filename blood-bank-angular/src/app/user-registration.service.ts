import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  constructor(private http: HttpClient) { }

  public doRegistration(user: any) {
    console.log("here");
    
    return (this.http.post("http://localhost:8090/signUp",user,{responseType:'text' as 'json'}));
  }
}
