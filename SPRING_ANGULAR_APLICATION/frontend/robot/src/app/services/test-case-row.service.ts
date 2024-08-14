import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { TestCaseRow } from '../entities/test-case-row';

@Injectable({
  providedIn: 'root'
})
export class TestCaseRowService {

constructor(private httpClient:HttpClient) { }


}
