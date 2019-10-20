// routerConfig.ts

import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ViewUsersComponent } from './view-users/view-users.component';

export const appRoutes: Routes = [
  { path: 'home', 
    component: HomeComponent 
  },
  { path: 'view-users', 
  component: ViewUsersComponent 
}
];
