import {Component, Inject, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {IServiceModel} from "../../medicalServices/medicalService.model";
import {IAppointment} from "../appointment.model";
import {EventEmitter} from '@angular/core';
import {IMedicalService, MEDICALSERVICE_SERVICE} from "../../medicalServices/medicalService.service";

@Component({
  selector: 'appointment-form',
  templateUrl: './appointment-form.component.html',
  styleUrls: ['./appointment-form.component.css']
})
export class AppointmentFormComponent implements OnInit {

  newAppointment: IAppointment;
  appointmentForm: FormGroup;

  services: Array<IServiceModel>;
  @Input()
  initialValues: IAppointment;

  @Output()
  private saveAppointment = new EventEmitter<FormGroup>();

  constructor(
    private formBuilder: FormBuilder,
    @Inject(MEDICALSERVICE_SERVICE) private medicalServiceService: IMedicalService
  ) {
    this.newAppointment = {}
  }

  onSubmit() {
    this.saveAppointment.emit(this.appointmentForm);
  }

  ngOnInit(): void {
    this.appointmentForm = this.formBuilder.group({
      petName: [this.initialValues.petName, Validators.required],
      doctorName: [this.initialValues.doctorName, Validators.required],
      datetime: [this.initialValues.dateTime, Validators.required],
      services: [[], Validators.required]
    })
    this.medicalServiceService.getServices()
      .subscribe(services => {
        this.services = services;
        this.appointmentForm
          .get('services')
          .setValue(this.services
            .filter(service => this.initialValues.services
              .some(selectedService => selectedService.id === service.id)))
      })
  }

}
