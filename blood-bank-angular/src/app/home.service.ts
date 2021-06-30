import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  public viewprofileservice(token: string) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<string>("http://localhost:8090/viewProfile", token, { headers, responseType: 'text' as 'json' });
  }

  public donatebloodservice(token: string) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<string>("http://localhost:8090/donateBlood",token,{ headers, responseType: 'text' as 'json' });
  }

  public requestbloodservice(token: string, bloodgroup: string) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post<string>("http://localhost:8090/requestBlood", bloodgroup, { headers, responseType: 'text' as 'json' });
  }
}
