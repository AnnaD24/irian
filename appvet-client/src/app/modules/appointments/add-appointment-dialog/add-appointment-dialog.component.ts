import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {IAppointment} from "../appointment.model";
import {FormGroup} from "@angular/forms";
import {APPOINTMENT_SERVICE, IAppointmentsService} from "../appointments.service";


@Component({
  selector: 'app-appointment-dialog',
  templateUrl: './add-appointment-dialog.component.html',
  styleUrls: ['./add-appointment-dialog.component.css']
})
export class AddAppointmentDialogComponent {
  public test: string = "control"

  initialValues: IAppointment = {
    petName: '',
    doctorName: '',
    dateTime: null,
    diagnostic: '',
    services: []
  }

  constructor(
    public dialogRef: MatDialogRef<AddAppointmentDialogComponent>,
    @Inject(APPOINTMENT_SERVICE) private appointmentService: IAppointmentsService
  ) {}

  onSubmit(formGroup: FormGroup) {
    let newAppointment: IAppointment = {
      petName: formGroup.value.petName,
      doctorName: formGroup.value.doctorName,
      dateTime: formGroup.value.dateTime,
      services: formGroup.value.services
    }
    this.appointmentService.saveAppointment(newAppointment).subscribe(

    );
    this.dialogRef.close()
  }
}
