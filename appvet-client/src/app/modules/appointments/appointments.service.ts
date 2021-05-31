import {Observable} from "rxjs";
import {InjectionToken} from "@angular/core";
import {IPage} from "./page.model";


export interface IAppointmentsService {
  getPagedAppointments(): Observable<IPage>;

  getPagedAppointmentsByDoctorName(name: string): Observable<IPage>;
}

export const APPOINTMENT_SERVICE = new InjectionToken<IAppointmentsService>('APPOINTMENT_SERVICE');
