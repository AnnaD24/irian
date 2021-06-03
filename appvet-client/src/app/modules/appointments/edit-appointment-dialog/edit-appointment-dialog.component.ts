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
export class EditAppointmentDialogComponent {
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
      diagnostic: data.appointment.diagnostic,
      services: data.appointment.services
    }
  }

  onSubmit(formGroup: FormGroup) {
    let newAppointment: IAppointment = {
      id: this.data.appointment.id,
      doctorName: formGroup.value.doctorName,
      diagnostic: formGroup.value.diagnostic,
      status: this.data.appointment.status,
      petName: formGroup.value.petName,
      dateTime: formGroup.value.dateTime,
      services: formGroup.value.services
    }
    this.appointmentService.modifyAppointment(newAppointment)
      .subscribe(
        data => Object.assign(this.data.appointment, data)
      );
    this.dialogRef.close()
  }
}
