import { Component, OnInit } from '@angular/core';
import { FunctionChange } from '../entities/function-change';
import { FunctionUpdate } from '../entities/function-update';
import { FunctionUpdateResponse } from '../entities/function-update-response';
import { MissingFunction } from '../entities/missing-function';
import { TestFunction } from '../entities/test-function';
import { FunctionService } from '../services/function.service';

@Component({
  selector: 'app-functions',
  templateUrl: './functions.component.html',
  styleUrls: ['./functions.component.css']
})
export class FunctionsComponent implements OnInit {
  successMessage: string = "";
  errorMessage: string = "";
  constructor(private functionService:FunctionService) { }
  missingFunctions:MissingFunction[]=[];
  changedFunctions:FunctionChange[]=[];
  newFunctions:TestFunction[]=[];

  ngOnInit() {
    this.functionService.getFunctionUpdate().subscribe(
      functionUpdate => this.initFunctions(functionUpdate),
      error => console.log(error)
    )
  }

  initFunctions(functionUpdate:FunctionUpdate){
    this.missingFunctions=[];
    this.changedFunctions=[];
    this.newFunctions=[];

    let newFunction = functionUpdate.newFunctions[0];
    functionUpdate.missingFunctions.forEach(
      func => this.missingFunctions.push({
        missingFunction:func,
        newFunction:newFunction,
        parameterMapping:this.initMissingFunctionParameterMapping(func.parameters,newFunction.parameters)
      })
    )
    this.changedFunctions=functionUpdate.changedFunctions;
    this.newFunctions=functionUpdate.newFunctions;
  }
  initMissingFunctionParameterMapping(oldParameters:string[],newParameters:string[]){
    let parameterMapping =[];
    for(let i=0;i<newParameters.length;i++){
      if(i<oldParameters.length){
        parameterMapping.push(oldParameters[i])
      }else{
        parameterMapping.push("default")
      }
    }
    return parameterMapping
  }
  onFunctionSelectionChange(missingFunction:MissingFunction){
    missingFunction.parameterMapping=this.initMissingFunctionParameterMapping(missingFunction.missingFunction.parameters
      ,missingFunction.newFunction.parameters);
    console.log(missingFunction);
  }
  updateFunctions(){
    let response:FunctionUpdateResponse={
      changedFunctions:this.changedFunctions,
      missingFunctions:this.missingFunctions,
      newFunctions:this.newFunctions
    }
    this.functionService.updateFunctions(response).subscribe(
      res => {this.ngOnInit();
    this.successMessage = "Successful update !";
    },
        error => {
          console.log(error);
          this.errorMessage = "update failed";
        }
    );
  }
}

