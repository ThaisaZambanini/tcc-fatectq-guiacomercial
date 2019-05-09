import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {ListUserComponent} from "./list-user/list-user.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import { HomeComponent } from './home/home.component';
import { ManterUfComponent } from './manter-uf/manter-uf.component';
import { AdicionarUfComponent } from './manter-uf/add-uf/add-uf.component';
import { AlterarUfComponent } from './manter-uf/edit-uf/edit-uf.component';
import { ManterFormaPagamentoComponent } from './manter-forma-pagamento/manter-forma-pagamento.component';
import { AdicionarPagamentoComponent } from './manter-forma-pagamento/add-forma-pagamento/add-forma-pagamento.component';
import { AlterarFormaPagamentoComponent } from './manter-forma-pagamento/edit-forma-pagamento/edit-forma-pagamento.component';

import { ManterCidadeComponent } from './manter-cidade/manter-cidade.component';
import { AdicionarCidadeComponent } from './manter-cidade/add-cidade/add-cidade.component';
import { EditCidadeComponent } from './manter-cidade/edit-cidade/edit-cidade.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {path: 'home', component: HomeComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'edit-user', component: EditUserComponent },
  { path: 'manter-uf', component: ManterUfComponent },
  { path: 'add-uf', component: AdicionarUfComponent },
  { path: 'edit-uf', component: AlterarUfComponent },
  { path: 'manter-forma-pagamento', component: ManterFormaPagamentoComponent },
  { path: 'add-forma-pagamento', component: AdicionarPagamentoComponent },
  { path: 'edit-forma-pagamento', component: AlterarFormaPagamentoComponent },
  { path: 'manter-cidade', component: ManterCidadeComponent },
  { path: 'add-cidade', component: AdicionarCidadeComponent },
  { path: 'edit-cidade', component: EditCidadeComponent }
];

export const routing = RouterModule.forRoot(routes);
