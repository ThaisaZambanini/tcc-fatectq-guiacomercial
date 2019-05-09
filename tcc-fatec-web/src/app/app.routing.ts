import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { AddUserComponent } from "./add-user/add-user.component";
import { ListUserComponent } from "./list-user/list-user.component";
import { EditUserComponent } from "./edit-user/edit-user.component";
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

import { ManterCategoriaComponent } from './manter-categoria/manter-categoria.component';
import { AddCategoriaComponent } from './manter-categoria/add-categoria/add-categoria.component';
import { EditCategoriaComponent } from './manter-categoria/edit-categoria/edit-categoria.component';

import { ManterEmpresaComponent } from './manter-empresa/manter-empresa.component';
import { AddEmpresaComponent } from './manter-empresa/add-empresa/add-empresa.component';
import { EditEmpresaComponent } from './manter-empresa/edit-empresa/edit-empresa.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'add-user', component: AddUserComponent },
  { path: 'list-user', component: ListUserComponent },
  { path: 'edit-user', component: EditUserComponent },

  /**ESTADO*/
  { path: 'manter-uf', component: ManterUfComponent },
  { path: 'add-uf', component: AdicionarUfComponent },
  { path: 'edit-uf', component: AlterarUfComponent },

  /**FORMA PAGAMENTO*/
  { path: 'manter-forma-pagamento', component: ManterFormaPagamentoComponent },
  { path: 'add-forma-pagamento', component: AdicionarPagamentoComponent },
  { path: 'edit-forma-pagamento', component: AlterarFormaPagamentoComponent },

  /**CIDADE*/
  { path: 'manter-cidade', component: ManterCidadeComponent },
  { path: 'add-cidade', component: AdicionarCidadeComponent },
  { path: 'edit-cidade', component: EditCidadeComponent },

  /**CATEGORIA*/
  { path: 'manter-categoria', component: ManterCategoriaComponent },
  { path: 'add-categoria', component: AddCategoriaComponent },
  { path: 'edit-categoria', component: EditCategoriaComponent },

  /**EMPRESA*/
  { path: 'manter-empresa', component: ManterEmpresaComponent },
  { path: 'add-empresa', component: AddEmpresaComponent },
  { path: 'edit-empresa', component: EditEmpresaComponent }
];

export const routing = RouterModule.forRoot(routes);
