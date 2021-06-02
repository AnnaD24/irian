import {NgModule} from "@angular/core";
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import {AppointmentsRoutingModule} from "./appointments-routing.module";
import {AppointmentServiceProvider} from "./appointment-rest.service";
import {CommonModule} from "@angular/common";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from '@angular/material/icon';
import { MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { AddAppointmentDialogComponent } from './add-appointment-dialog/add-appointment-dialog.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from "@angular/material/input";
import {MedicalServiceModule} from "../medicalServices/medicalService.module";
import {NgxMatDatetimePickerModule, NgxMatNativeDateModule} from "@angular-material-components/datetime-picker";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatPaginatorModule} from '@angular/material/paginator';
import { EditAppointmentDialogComponent } from './edit-appointment-dialog/edit-appointment-dialog.component';
import { AppointmentFormComponent } from './appointment-form/appointment-form.component';

@NgModule({
  imports: [
    AppointmentsRoutingModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    MatDialogModule,
    MatInputModule,
    ReactiveFormsModule,
    MedicalServiceModule,
    NgxMatNativeDateModule,
    NgxMatDatetimePickerModule,
    MatDatepickerModule,
    MatPaginatorModule
  ],
  declarations: [
    AppointmentListComponent,
    AddAppointmentDialogComponent,
    EditAppointmentDialogComponent,
    AppointmentFormComponent,
  ],
  providers: [AppointmentServiceProvider]
})
export class AppointmentsModule {
}
