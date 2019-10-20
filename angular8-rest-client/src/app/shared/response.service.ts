import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Response } from './Response';
import { User } from './User'
@Injectable({
  providedIn: 'root'
})
export class ResponseService {

  //constructor() { }


  // Define API
  apiURL = 'http://localhost:4599/api';

  constructor(private http: HttpClient) { }

  /*========================================
    CRUD Methods for consuming RESTful API
  =========================================*/

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  // HttpClient API get() method => Fetch Users list
  getUsers(): Observable<Response> {
    return this.http.get<Response>(this.apiURL + '/users')
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }
  // HttpClient API post() method => Create User
  createUser(user: User): Observable<Response> {
    console.log(JSON.stringify(user))
    return this.http.put<Response>(this.apiURL + '/users', user, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }
  // HttpClient API delete() method => Delete User
  deleteUser(username) {
    console.log('deleteUser', this.apiURL + '/delete/' + username);
    return this.http.put<Response>(this.apiURL + '/delete/' + username, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }
  /*
    // HttpClient API get() method => Fetch User
    getUser(id): Observable<User> {
      return this.http.get<User>(this.apiURL + '/Users/' + id)
        .pipe(
          retry(1),
          catchError(this.handleError)
        )
    }
  
    // HttpClient API put() method => Update User
    updateUser(id, User): Observable<User> {
      return this.http.put<User>(this.apiURL + '/Users/' + id, JSON.stringify(User), this.httpOptions)
        .pipe(
          retry(1),
          catchError(this.handleError)
        )
    }
  

  */
  // Error handling 
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }

}

