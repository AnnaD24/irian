import {Component, Inject, OnInit} from '@angular/core';
import {IAppointment} from "../appointment.model";
import {APPOINTMENT_SERVICE, IAppointmentsService} from "../appointments.service";
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {AddAppointmentDialogComponent} from "../add-appointment-dialog/add-appointment-dialog.component";
import {EditAppointmentDialogComponent} from "../edit-appointment-dialog/edit-appointment-dialog.component";

@Component({
  selector: 'app-appointments',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent {

  public appointments: Array<IAppointment>;
  public doctors: Set<string>;
  selected: string = 'all';

  constructor(@Inject(APPOINTMENT_SERVICE) private appointmentService: IAppointmentsService,
              public dialog: MatDialog) {
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

  openEditDialog(appointment: IAppointment): void {
    this.dialog.open(EditAppointmentDialogComponent, {
      width: '500px',
      data: {appointment: appointment},
    });
  }

  openAddDialog(): void {
    this.dialog.open(AddAppointmentDialogComponent, {
      width: '500px',
    });
  }
}
