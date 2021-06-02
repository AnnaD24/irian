import {Component, Inject} from '@angular/core';
import {IAppointment} from "../appointment.model";
import {FormGroup} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {APPOINTMENT_SERVICE, IAppointmentsService} from "../appointments.service";

export interface DialogData {
  appointment: IAppointment;
}

@Component({
  selector: 'app-edit-appointment-dialog',
  templateUrl: './edit-appointment-dialog.component.html',
  styleUrls: ['./edit-appointment-dialog.component.css']
})
export class EditAppointmentDialogComponent{
  public test: string = "control"

  initialValues: IAppointment;

  constructor(
    public dialogRef: MatDialogRef<EditAppointmentDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    @Inject(APPOINTMENT_SERVICE) private appointmentService: IAppointmentsService
  ) {
    this.initialValues = {
      petName: data.appointment.petName,
      doctorName: data.appointment.doctorName,
      dateTime: data.appointment.dateTime,
      services: data.appointment.services
    }
  }

  onSubmit(formGroup: FormGroup) {
    let newAppointment: IAppointment = {
      petName: formGroup.value.petName,
      doctorName: formGroup.value.doctorName,
      dateTime: formGroup.value.datetime,
      services: formGroup.value.services
    }
    console.log(newAppointment)
    this.appointmentService.modifyAppointment(newAppointment).subscribe(data => console.log(data));
    this.dialogRef.close()
  }
}
