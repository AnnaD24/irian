import {NgModule} from "@angular/core";
import { MedicalServiceListComponent } from './medicalService-list/medicalService-list.component';
import {MedicalServiceRoutingModule} from "./medicalService-routing.module";
import {MedicalServiceServiceProvider} from "./medicalService-rest.service";
import {MatCardModule} from "@angular/material/card";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";

@NgModule({
  imports: [
    MedicalServiceRoutingModule,
    MatCardModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
  ],
  declarations: [
    MedicalServiceListComponent,
  ],
  providers: [MedicalServiceServiceProvider]
})
export class MedicalServiceModule {
}
