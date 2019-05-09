import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {ListUserComponent} from "./list-user/list-user.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import { HomeComponent } from './home/home.component';
import { ManterUfComponent } from './manter-uf/manter-uf.component';
import { AdicionarUfComponent } from './manter-uf/add-uf/add-uf.component';
import { AlterarUfComponent } from './manter-uf/edit-uf/edit-uf.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {path: 'home', component: HomeComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: 'manter-uf', component: ManterUfComponent },
    { path: 'add-uf', component: AdicionarUfComponent },
    { path: 'edit-uf', component: AlterarUfComponent }
];

export const routing = RouterModule.forRoot(routes);
