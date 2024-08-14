import { EventEmitter, Injectable } from '@angular/core';
import { Alert } from '../entities/alert';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  alert:Alert={
  status:"",
  text:"",
  }
  alertChange: EventEmitter<Alert> = new EventEmitter();

constructor() { }
sucess(txt){
  this.alert.status="success"
  this.alert.text=txt
  this.alertChange.emit(this.alert)
}
error(txt){
  this.alert.status="error"
  this.alert.text=txt
  this.alertChange.emit(this.alert)
}


}
