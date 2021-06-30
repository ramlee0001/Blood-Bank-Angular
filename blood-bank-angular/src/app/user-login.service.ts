import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDetails } from './LoginDetails';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  constructor(private http: HttpClient) { }

  public generateToken(request: LoginDetails) {
    return this.http.post<string>("http://localhost:8090/authenticate", request, { responseType: 'text' as 'json' });
  }
  public welcome(token: string) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<string>("http://localhost:8090/", { headers, responseType: 'text' as 'json' });
  }
  
}
