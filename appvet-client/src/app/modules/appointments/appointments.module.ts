import {NgModule} from "@angular/core";
import { AppointmentListComponent } from './appointment-list/appointment-list.component';
import {AppointmentsRoutingModule} from "./appointments-routing.module";
import {AppointmentServiceProvider} from "./appointment-rest.service";
import {CommonModule} from "@angular/common";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';

@NgModule({
  imports: [
    AppointmentsRoutingModule,
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  declarations: [
    AppointmentListComponent
  ],
  providers: [AppointmentServiceProvider]
})
export class AppointmentsModule {
}
