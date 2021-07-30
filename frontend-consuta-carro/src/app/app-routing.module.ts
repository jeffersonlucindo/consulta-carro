import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

import { HomeComponent } from "./views/home/home.component";
import { CarroComponent } from "./components/carro/carro.component";
import { CreateComponent } from "./components/carro/create/create.component";
import { UpdateComponent } from "./components/carro/update/update.component";
import { MarcaComponent } from "./components/marca/marca.component";



const routes: Routes = [
    {
      path: "",
      component: HomeComponent
    },
    {
      path: "carro",
      component: CarroComponent
    },
    {
      path: "marca",
      component: MarcaComponent
    },
    
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
