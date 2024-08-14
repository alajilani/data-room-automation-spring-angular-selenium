import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { TestCase } from '../entities/test-case';
import { TestSuite } from '../entities/test-suite';

@Injectable({
  providedIn: 'root'
})
export class TestSuiteService {

constructor(private httpClient:HttpClient) { }

getTestSuites(){
  return this.httpClient.get<TestSuite[]>(BASE_URL+"/testsuite/get/all");
}
deleteTestSuite(testSuiteId:number){
  return this.httpClient.delete(BASE_URL+"/testsuite/delete/"+testSuiteId)
}
saveTestCase(testSuiteId:number,testCase:TestCase){
  return this.httpClient.post<TestCase>(BASE_URL+"/testsuite/"+testSuiteId+"/testcase/save",testCase);
}
saveTestSuite(testSuite:TestSuite){
  return this.httpClient.post<TestSuite>(BASE_URL+"/testsuite/save",testSuite);
}

}
