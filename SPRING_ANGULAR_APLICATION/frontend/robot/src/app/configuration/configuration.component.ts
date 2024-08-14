import { Component, OnInit } from '@angular/core';
import { Configuration } from '../entities/configuration';
import { ConfigurationService } from '../services/configuration.service';

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

  constructor(private configurationService:ConfigurationService) { }

  configuration:Configuration;
  orignalConfiguration:Configuration;
  successMessage: string = "";
  errorMessage: string = "";

  ngOnInit() {
    this.configurationService.getConfiguration().subscribe(
      (configuration) => {
        this.orignalConfiguration=configuration;
        this.configuration=configuration;
      },
      error => console.log(error)
    )
  }
  update(){
    this.configurationService.updateConfiguration(this.configuration).subscribe(
      config =>{
        this.configuration=config;
        this.orignalConfiguration=config;
        this.successMessage = "Successful update !";
      },
      error=>{
        console.log(error);
        this.configuration=this.orignalConfiguration;
        this.errorMessage = "update failed";
      }
    )
  }

}
