import {Observable} from "rxjs";
import {InjectionToken} from "@angular/core";
import {IPage} from "./page.model";
import {IAppointment} from "./appointment.model";


export interface IAppointmentsService {
  getPagedAppointments(pageIndex: number): Observable<IPage>;

  getPagedAppointmentsByDoctorName(name: string): Observable<IPage>;

  saveAppointment(newAppointment: IAppointment): Observable<any>;

  modifyAppointment(newAppointment: IAppointment): Observable<any>;

  getAllDoctors(): Observable<any>;
}

export const APPOINTMENT_SERVICE = new InjectionToken<IAppointmentsService>('APPOINTMENT_SERVICE');
