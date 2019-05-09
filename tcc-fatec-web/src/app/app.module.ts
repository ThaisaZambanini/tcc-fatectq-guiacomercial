import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { routing } from "./app.routing";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { HttpModule } from '@angular/http';
import { AddUserComponent } from './add-user/add-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ListUserComponent } from "./list-user/list-user.component";
import { UserService } from "./service/user.service";
import { EstadoService } from "./service/estado.service";
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { NgxMaskModule } from 'ngx-mask';
import { ManterUfComponent } from './manter-uf/manter-uf.component';
import { AdicionarUfComponent } from './manter-uf/add-uf/add-uf.component';
import { AlterarUfComponent } from './manter-uf/edit-uf/edit-uf.component';
import { NgFlashMessagesModule } from 'ng-flash-messages';
import { NgxLoadingModule, ngxLoadingAnimationTypes } from 'ngx-loading';

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
    AlterarUfComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule,
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
  providers: [UserService, EstadoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
