import {Component, Inject, OnInit} from '@angular/core';
import {IMedicalService, MEDICALSERVICE_SERVICE} from "../medicalService.service";
import {IServiceModel} from "../medicalService.model";
import {MatDialog} from "@angular/material/dialog";
import {AddMedicalServiceDialogComponent} from "../add-medicalService-dialog/add-medicalService-dialog.component";

@Component({
  selector: 'app-services',
  templateUrl: './medicalService-list.component.html',
  styleUrls: ['./medicalService-list.component.css']
})
export class MedicalServiceListComponent implements OnInit {

  public services: Array<IServiceModel>;

  constructor(
    @Inject(MEDICALSERVICE_SERVICE) private medicalServiceService: IMedicalService,
    public dialog: MatDialog
  ) {
    this.medicalServiceService.getServices()
      .subscribe(
        services => this.services = services
      )
  }

  ngOnInit(): void {
  }

  openSurveyDialog() {
    this.dialog.open(AddMedicalServiceDialogComponent, {
      width: '500px',
    });
  }
}
