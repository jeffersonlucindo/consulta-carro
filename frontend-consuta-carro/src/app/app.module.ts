import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/templates/header/header.component';

import { MatToolbarModule } from '@angular/material/toolbar';
import { FooterComponent } from './components/templates/footer/footer.component';
import { NavComponent } from './components/templates/nav/nav.component';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule,ReactiveFormsModule }   from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HomeComponent } from './views/home/home.component';
import { CarroComponent } from './components/carro/carro.component';
import { CreateComponent } from './components/carro/create/create.component';
import { MarcaComponent } from './components/marca/marca.component';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { HeadComponent } from './components/carro/head/head.component';
import { UpdateComponent } from './components/carro/update/update.component';
import { DeleteComponent } from './components/carro/delete/delete.component';
import { DetailsComponent } from './components/carro/details/details.component';
import { MarcaCreateComponent } from './components/marca/marca-create/marca-create.component';
import { MarcaUpdateComponent } from './components/marca/marca-update/marca-update.component';
import { MarcaDeleteComponent } from './components/marca/marca-delete/marca-delete.component';
import { MarcaHeadComponent } from './components/marca/marca-head/marca-head.component';
import { MatNativeDateModule } from '@angular/material/core';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    HomeComponent,
    CarroComponent,
    CreateComponent,
    MarcaComponent,
    HeadComponent,
    UpdateComponent,
    DeleteComponent,
    DetailsComponent,
    MarcaCreateComponent,
    MarcaUpdateComponent,
    MarcaDeleteComponent,
    MarcaHeadComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule
    

    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
