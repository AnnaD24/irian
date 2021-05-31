import {APPOINTMENT_SERVICE, IAppointmentsService} from "./appointments.service";
import {Injectable, Provider} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IAppointment} from "./appointment.model";
import {IPage} from "./page.model";

@Injectable()
export class AppointmentRestService implements IAppointmentsService {
  private url: string = `http://localhost:8080/rest/appointments/paged?pageNumber=1&pageSize=5&sort.sorted=true`;

  constructor(private http: HttpClient) {
  }

  getPagedAppointments(): Observable<IPage> {
    return this.http.get<IPage>(this.url);
  }
}

export const AppointmentServiceProvider: Provider = {
  provide: APPOINTMENT_SERVICE,
  useClass: AppointmentRestService
}
