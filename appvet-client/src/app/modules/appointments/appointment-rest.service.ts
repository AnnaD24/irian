import {APPOINTMENT_SERVICE, IAppointmentsService} from "./appointments.service";
import {Injectable, Provider} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IPage} from "./page.model";
import {IAppointment} from "./appointment.model";

@Injectable()
export class AppointmentRestService implements IAppointmentsService {
  private url: string = `http://localhost:8080/rest/appointments`;

  constructor(private http: HttpClient) {
  }

  getPagedAppointments(pageIndex: number): Observable<IPage> {
    return this.http.get<IPage>(this.url + '?pageSize=5&pageNo=' + pageIndex + '&sortBy=dateTime');
  }

  getPagedAppointmentsByDoctorName(name: string): Observable<IPage> {
    return this.http.get<IPage>(this.url + '/' + name);
  }

  modifyAppointment(newAppointment: IAppointment): Observable<any> {
    return this.http.put(
      this.url,
      newAppointment
    );
  }

  saveAppointment(newAppointment: IAppointment): Observable<any> {
    return this.http.post(
      this.url,
      newAppointment
    );
  }
}

export const AppointmentServiceProvider: Provider = {
  provide: APPOINTMENT_SERVICE,
  useClass: AppointmentRestService
}
