import { RouterModule, Routes, CanActivate } from '@angular/router';
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

import { ConsultaMensagemComponent } from './consulta-mensagem/consulta-mensagem.component';
import {AuthGuardService as AuthGuard} from "./service/auth-guard.service";

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: "mensagem", component: ConsultaMensagemComponent, canActivate: [AuthGuard]},
  { path: 'add-user', component: AddUserComponent},
  { path: 'list-user', component: ListUserComponent },
  { path: 'edit-user', component: EditUserComponent },

  /**ESTADO*/
  { path: 'manter-uf', component: ManterUfComponent,canActivate: [AuthGuard] },
  { path: 'add-uf', component: AdicionarUfComponent,canActivate: [AuthGuard] },
  { path: 'edit-uf', component: AlterarUfComponent,canActivate: [AuthGuard] },

  /**FORMA PAGAMENTO*/
  { path: 'manter-forma-pagamento', component: ManterFormaPagamentoComponent, canActivate: [AuthGuard] },
  { path: 'add-forma-pagamento', component: AdicionarPagamentoComponent, canActivate: [AuthGuard] },
  { path: 'edit-forma-pagamento', component: AlterarFormaPagamentoComponent, canActivate: [AuthGuard] },

  /**CIDADE*/
  { path: 'manter-cidade', component: ManterCidadeComponent, canActivate: [AuthGuard] },
  { path: 'add-cidade', component: AdicionarCidadeComponent, canActivate: [AuthGuard] },
  { path: 'edit-cidade', component: EditCidadeComponent, canActivate: [AuthGuard] },

  /**CATEGORIA*/
  { path: 'manter-categoria', component: ManterCategoriaComponent, canActivate: [AuthGuard] },
  { path: 'add-categoria', component: AddCategoriaComponent, canActivate: [AuthGuard] },
  { path: 'edit-categoria', component: EditCategoriaComponent, canActivate: [AuthGuard] },

  /**EMPRESA*/
  { path: 'manter-empresa', component: ManterEmpresaComponent, canActivate: [AuthGuard] },
  { path: 'add-empresa', component: AddEmpresaComponent, canActivate: [AuthGuard] },
  { path: 'edit-empresa', component: EditEmpresaComponent, canActivate: [AuthGuard] }
];

export const routing = RouterModule.forRoot(routes);
