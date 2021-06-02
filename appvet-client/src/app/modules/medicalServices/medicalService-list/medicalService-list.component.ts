import {Component, Inject, OnInit} from '@angular/core';
import {IMedicalService, MEDICALSERVICE_SERVICE} from "../medicalService.service";
import {IServiceModel} from "../medicalService.model";

@Component({
  selector: 'app-services',
  templateUrl: './medicalService-list.component.html',
  styleUrls: ['./medicalService-list.component.css']
})
export class MedicalServiceListComponent implements OnInit {

  public services: Array<IServiceModel>;

  constructor(@Inject(MEDICALSERVICE_SERVICE) private medicalServiceService: IMedicalService) {
    this.medicalServiceService.getServices()
      .subscribe(services => {
        console.log(services);
        this.services = services;
      })
  }

  ngOnInit(): void {
  }

}
