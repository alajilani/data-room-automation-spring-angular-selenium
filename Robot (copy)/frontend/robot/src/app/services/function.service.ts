import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { BASE_URL } from 'src/environments/environment';
import { TestFunction } from '../entities/test-function';
import { FunctionUpdate } from '../entities/function-update';

@Injectable({
  providedIn: 'root'
})
export class FunctionService {

constructor( 
  private httpClient:HttpClient) {}
  getFunctions(){
    return this.httpClient.get<TestFunction[]>(BASE_URL+"/function/get/all");
  }
  deleteFunction(functionId : number){
    return this.httpClient.delete(BASE_URL+"/function/delete/"+functionId);
  }
  getFunctionUpdate(){
    return this.httpClient.get<FunctionUpdate>(BASE_URL+"/function/get/update");
  }
  updateFunctions(response){
    return this.httpClient.post(BASE_URL+"/function/update/functions",response);
  }

}
