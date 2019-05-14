import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { routing } from "./app.routing";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { HttpModule } from '@angular/http';
import { FormsModule } from '@angular/forms';

/**SERVICES*/
import { UserService } from "./service/user.service";
import { EstadoService } from "./service/estado.service";
import { CidadeService } from "./service/cidade.service";
import { FormaPagamentoService } from "./service/forma-pagamento.service";
import { CategoriaService } from "./service/categoria.service";
import { EmpresaService } from "./service/empresa.service";
import { MensagemUsuarioService } from "./service/mensagem-usuario.service";

/**LIBS*/
import { NgxPaginationModule } from 'ngx-pagination';
import { NgxMaskModule } from 'ngx-mask';
import { NgFlashMessagesModule } from 'ng-flash-messages';
import { NgxLoadingModule, ngxLoadingAnimationTypes } from 'ngx-loading';

/**COMPONENTES*/
import { AddUserComponent } from './add-user/add-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ListUserComponent } from "./list-user/list-user.component";

import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';

import { ManterUfComponent } from './manter-uf/manter-uf.component';
import { AdicionarUfComponent } from './manter-uf/add-uf/add-uf.component';
import { AlterarUfComponent } from './manter-uf/edit-uf/edit-uf.component';

import { ManterFormaPagamentoComponent } from './manter-forma-pagamento/manter-forma-pagamento.component';
import { AdicionarPagamentoComponent } from './manter-forma-pagamento/add-forma-pagamento/add-forma-pagamento.component';
import { AlterarFormaPagamentoComponent } from './manter-forma-pagamento/edit-forma-pagamento/edit-forma-pagamento.component';
import { ManterCidadeComponent } from './manter-cidade/manter-cidade.component';
import { AdicionarCidadeComponent } from './manter-cidade/add-cidade/add-cidade.component';
import { EditCidadeComponent } from './manter-cidade/edit-cidade/edit-cidade.component';

import { ManterEmpresaComponent } from './manter-empresa/manter-empresa.component';
import { AddEmpresaComponent } from './manter-empresa/add-empresa/add-empresa.component';
import { EditEmpresaComponent } from './manter-empresa/edit-empresa/edit-empresa.component';

import { ManterCategoriaComponent } from './manter-categoria/manter-categoria.component';
import { AddCategoriaComponent } from './manter-categoria/add-categoria/add-categoria.component';
import { EditCategoriaComponent } from './manter-categoria/edit-categoria/edit-categoria.component';

import { ConsultaMensagemComponent } from './consulta-mensagem/consulta-mensagem.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListUserComponent,
    AddUserComponent,
    EditUserComponent,
    HomeComponent,
    MenuComponent,
    ManterUfComponent,
    AdicionarUfComponent,
    AlterarUfComponent,
    ManterFormaPagamentoComponent,
    AdicionarPagamentoComponent,
    AlterarFormaPagamentoComponent,
    ManterCidadeComponent,
    AdicionarCidadeComponent,
    EditCidadeComponent,
    ManterEmpresaComponent,
    AddEmpresaComponent,
    EditEmpresaComponent,
    ManterCategoriaComponent,
    AddCategoriaComponent,
    EditCategoriaComponent,
    ConsultaMensagemComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
    NgxMaskModule.forRoot(),
    HttpModule,
    NgFlashMessagesModule.forRoot(),
    NgxLoadingModule.forRoot({
      fullScreenBackdrop: true,
      primaryColour: '#ffffff',
      secondaryColour: '#ffffff',
      tertiaryColour: '#ffffff'
    })
  ],
  providers: [UserService, EstadoService, CidadeService, FormaPagamentoService, CategoriaService, MensagemUsuarioService, EmpresaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
