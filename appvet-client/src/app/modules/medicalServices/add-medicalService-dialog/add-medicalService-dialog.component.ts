import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {IMedicalService, MEDICALSERVICE_SERVICE} from "../medicalService.service";
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-survey-dialog',
  templateUrl: './add-medicalService-dialog.component.html',
  styleUrls: ['./add-medicalService-dialog.component.css']
})
export class AddMedicalServiceDialogComponent implements OnInit {

  serviceForm: FormGroup;
  minPrice = 50;

  constructor(
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<AddMedicalServiceDialogComponent>,
    @Inject(MEDICALSERVICE_SERVICE) private medicalServiceService: IMedicalService
  ) {}

  priceValidation() {
    return (control: AbstractControl): ValidationErrors | any => {
        if (control.value < this.minPrice) {
          return {
            'price-validator': true
          };
        }
      return undefined;
    };
  }


  ngOnInit(): void {
    this.serviceForm = this.formBuilder.group({
      name: new FormControl('',[Validators.required]),
      price: new FormControl('',[Validators.required, this.priceValidation()])
    })
  }

  onSubmit() {
    this.dialogRef.close()
    this.medicalServiceService.saveService(this.serviceForm.value).subscribe(service => {
    });
  }

  get price() {return this.serviceForm.get('price')}

  get name() {return this.serviceForm.get('name')}
}
