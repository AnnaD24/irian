import {Component, EventEmitter, Inject, Input, OnInit, Output} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {IServiceModel} from "../../medicalServices/medicalService.model";
import {IAppointment} from "../appointment.model";
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
      dateTime: [this.initialValues.dateTime, Validators.required],
      diagnostic: [this.initialValues.diagnostic, this.diagnosticValidation()],
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

  diagnosticValidation() {
    return (control: AbstractControl): ValidationErrors | any => {
      if (control.value) {
        if (control.value.trim().length < 4) {
          return {
            'price-validator': true
          };
        }
      }
      return undefined;
    };
  }
}
