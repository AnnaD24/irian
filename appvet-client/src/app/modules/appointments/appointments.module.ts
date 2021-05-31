import {NgModule} from "@angular/core";
import { AppointmentsComponent } from './appointments/appointments.component';
import {AppointmentsRoutingModule} from "./appointments-routing.module";
import {AppointmentServiceProvider} from "./appointment-rest.service";

@NgModule({
  imports: [
    AppointmentsRoutingModule
  ],
  declarations: [
    AppointmentsComponent
  ],
  providers: [AppointmentServiceProvider]
})
export class AppointmentsModule {
}
