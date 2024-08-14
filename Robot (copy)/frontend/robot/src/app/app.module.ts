import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {DropdownModule} from 'primeng/dropdown';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { authInterceptorProviders } from './services/AuthInterceptor.service';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TestCaseComponent } from './test-case/test-case.component';
import { TestListComponent } from './test-list/test-list.component';
import {ButtonModule} from 'primeng/button';
import {SlideMenuModule} from 'primeng/slidemenu';
import {MenuModule} from 'primeng/menu';
import {DragDropModule} from 'primeng/dragdrop';
import {TableModule} from 'primeng/table';
import { HttpClientModule } from '@angular/common/http';
import {InputTextModule} from 'primeng/inputtext';
import { FormsModule } from '@angular/forms'; 
import {DialogModule} from 'primeng/dialog';
import {CheckboxModule} from 'primeng/checkbox';
import {TieredMenuModule} from 'primeng/tieredmenu';
import {MultiSelectModule} from 'primeng/multiselect';
import {CardModule} from 'primeng/card';
import {AccordionModule} from 'primeng/accordion';
import {MessageModule} from 'primeng/message';




import {TabViewModule} from 'primeng/tabview';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { UsersComponent } from './users/users.component';
import { FunctionsComponent } from './functions/functions.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { LoginpageComponent } from './loginpage/loginpage.component';







@NgModule({
  declarations: [							
    AppComponent,
      TestCaseComponent,
      TestListComponent,
      LoginComponent,
      HomeComponent,
      UsersComponent,
      FunctionsComponent,
      ConfigurationComponent,
      ConfirmationDialogComponent,
      LoginpageComponent
   ],
  imports: [
    HttpClientModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    DropdownModule,
    ButtonModule,
    SlideMenuModule,
    CheckboxModule,
    MenuModule,
    DragDropModule,
    TableModule,
    InputTextModule,
    DialogModule,
    TabViewModule,
    TieredMenuModule,
    MultiSelectModule,
    CardModule,
    AccordionModule,
    MessageModule,
    BrowserAnimationsModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
