import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from 'src/environments/environment';
import { Configuration } from '../entities/configuration';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {

constructor(private httpClient:HttpClient) { }

getConfiguration(){
  return this.httpClient.get<Configuration>(BASE_URL+"/configuration/get");
}
updateConfiguration(configuration:Configuration){
  return this.httpClient.put<Configuration>(BASE_URL+"/configuration/update",configuration);
}

}
