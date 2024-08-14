import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from 'src/environments/environment';
import { Login } from '../entities/login';
import { LoginResponse } from '../entities/login-response';

@Injectable({
  providedIn: 'root',
})
export class SignInService {
  constructor(private http: HttpClient) {}
  login(login: Login): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(
      BASE_URL + '/auth/signin',
      login
    );
  }
}
