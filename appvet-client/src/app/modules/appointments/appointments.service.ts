import {Observable} from "rxjs";
import {IAppointment} from "./appointment.model";
import {InjectionToken} from "@angular/core";
import {IPage} from "./page.model";


export interface IAppointmentsService {
  getPagedAppointments(): Observable<IPage>;
}

export const APPOINTMENT_SERVICE = new InjectionToken<IAppointmentsService>('APPOINTMENT_SERVICE');
