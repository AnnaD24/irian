import {Component, Inject, OnInit} from '@angular/core';
import {IAppointment} from "../appointment.model";
import {APPOINTMENT_SERVICE, IAppointmentsService} from "../appointments.service";

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  private appointments: Array<IAppointment>;

  constructor(@Inject(APPOINTMENT_SERVICE) private appointmentService: IAppointmentsService) {
    this.appointmentService.getPagedAppointments()
      .subscribe(page => {
        console.log(page.content)
        this.appointments = page.content;
      });
  }

  ngOnInit(): void {
  }

}
