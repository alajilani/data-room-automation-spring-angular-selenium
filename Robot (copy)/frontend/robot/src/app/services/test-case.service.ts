import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { TestCaseRow } from '../entities/test-case-row';
import { TestCaseUpdate } from '../entities/test-case-update';

@Injectable({
  providedIn: 'root'
})
export class TestCaseService {

  constructor(private httpClient:HttpClient) {}

  updateTestCase(testCaseUpdate : TestCaseUpdate){
    return this.httpClient.put(BASE_URL+"/testcase/update",testCaseUpdate)
  }
  deleteTestCase(testCaseId:number){
    return this.httpClient.delete(BASE_URL+"/testcase/delete/"+testCaseId)
  }
  getTestCaseRowsByTestCaseId(testCaseId:number){
    return this.httpClient.get<TestCaseRow[]>(BASE_URL+"/testcase/"+testCaseId+"/testcaserow/get/all")
  }
  executeTestCase(testCaseId:number){
    return this.httpClient.get(BASE_URL+"/testcase/execute/"+testCaseId,{responseType: 'blob'});
  }
  cloneTestCase(testCaseCloneDto:TestCaseCloneDto) {
    let url = BASE_URL + "/testcase/clone";
    return this.httpClient.post(url,testCaseCloneDto)
  }
}
export class TestCaseCloneDto {
  testSuiteId: number;
  testCaseId : number;
  testCaseName: string;
  constructor(){
  }
}