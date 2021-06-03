import {NgModule} from "@angular/core";
import { MedicalServiceListComponent } from './medicalService-list/medicalService-list.component';
import {MedicalServiceRoutingModule} from "./medicalService-routing.module";
import {MedicalServiceServiceProvider} from "./medicalService-rest.service";
import {MatCardModule} from "@angular/material/card";
import {CommonModule} from "@angular/common";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { AddMedicalServiceDialogComponent } from './add-medicalService-dialog/add-medicalService-dialog.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  imports: [
    MedicalServiceRoutingModule,
    MatCardModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule,
  ],
  declarations: [
    MedicalServiceListComponent,
    AddMedicalServiceDialogComponent,
  ],
  providers: [MedicalServiceServiceProvider]
})
export class MedicalServiceModule {
}
