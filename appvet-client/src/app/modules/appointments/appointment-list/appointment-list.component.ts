import {Component, Inject, OnInit} from '@angular/core';
import {IAppointment} from "../appointment.model";
import {APPOINTMENT_SERVICE, IAppointmentsService} from "../appointments.service";
import {MatSelectChange} from "@angular/material/select";
import {MatDialog} from "@angular/material/dialog";
import {AddAppointmentDialogComponent} from "../add-appointment-dialog/add-appointment-dialog.component";
import {EditAppointmentDialogComponent} from "../edit-appointment-dialog/edit-appointment-dialog.component";
import {Status} from "../appointment-status.model";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-appointments',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  appointments: Array<IAppointment>;
  doctors: Set<string>;

  selected: string = 'all';

  pageIndex: number = 0;
  totalElems: number;

  constructor(@Inject(APPOINTMENT_SERVICE) private appointmentService: IAppointmentsService,
              public dialog: MatDialog) {
  }

  getAllAppointments() {
    this.appointmentService.getPagedAppointments(this.pageIndex)
      .subscribe(page => {
        this.totalElems = page.totalElements;
        this.appointments = page.content;
        console.log(this.appointments)
        this.doctors = new Set(this.appointments.map(appointment => appointment.doctorName));
      });
  }

  completeAppointment(appointment: IAppointment) {
    this.appointmentService.modifyAppointment({...appointment, status: Status.COMPLETED})
      .subscribe(data => {
        appointment.status = data.status
        alert("Appointment for " + data.petName + " set to completed.")
      });
  }

  confirmAppointment(appointment: IAppointment) {
    this.appointmentService.modifyAppointment({...appointment, status: Status.CONFIRMED})
      .subscribe(data =>{
        appointment.status = data.status
        alert("Appointment for " + appointment.petName + " set to confirmed.")
      });
  }

  onDoctorChange($event: MatSelectChange) {
    if ($event.value === 'all')
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

  ngOnInit(): void {
    this.getAllAppointments();
  }

  onPageChanged($event: PageEvent) {
    this.pageIndex = $event.pageIndex;
    this.getAllAppointments();
  }
}
