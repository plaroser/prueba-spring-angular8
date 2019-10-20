import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

import { RouterModule } from '@angular/router';

import { appRoutes } from './routerConfig';
import { ViewUsersComponent } from './view-users/view-users.component';
import { HttpClientModule } from '@angular/common/http';
import { ResponseService } from './shared/response.service';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ViewUsersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ResponseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
