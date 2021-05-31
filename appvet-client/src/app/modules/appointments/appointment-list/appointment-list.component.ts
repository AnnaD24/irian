import {Component, Inject, OnInit} from '@angular/core';
import {IAppointment} from "../appointment.model";
import {APPOINTMENT_SERVICE, IAppointmentsService} from "../appointments.service";
import {MatSelectChange} from "@angular/material/select";
import {$e} from "codelyzer/angular/styles/chars";

@Component({
  selector: 'app-appointments',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  public appointments: Array<IAppointment>;
  public doctors: Set<string>;
  selected: string = 'all';

  constructor(@Inject(APPOINTMENT_SERVICE) private appointmentService: IAppointmentsService) {
    this.getAllAppointments();
  }

  getAllAppointments() {
    this.appointmentService.getPagedAppointments()
      .subscribe(page => {
        this.appointments = page.content;
        this.doctors = new Set(this.appointments.map(appointment => appointment.doctorName));
      });
  }

  onDoctorChange($event: MatSelectChange) {
    if($event.value === 'all')
      this.getAllAppointments();

    this.appointmentService.getPagedAppointmentsByDoctorName($event.value)
      .subscribe(page => {
        this.appointments = page.content;
      })
  }

  ngOnInit(): void {
  }
}
