import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { Library } from '../entities/library';

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

constructor(private httpClient:HttpClient) { }
getLibraries(){
  return this.httpClient.get<Library[]>(BASE_URL+"/library/get/all");
}

}
